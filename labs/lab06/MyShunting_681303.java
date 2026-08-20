package labs.lab06;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class MyShunting_681303 {
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String strNum) {
        if (strNum == null)
            return false;
        return pattern.matcher(strNum).matches();
    }
    
    private static int order(String c) {
        return switch (c) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    public static String infixToPostfix(String infixString) {
        MyQueueL_681303<String> queue = new MyQueueL_681303<>();
        MyStack_681303<String> stack = new MyStack_681303<>();

        StringTokenizer st = new StringTokenizer(infixString);
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if(isNumeric(t)){
                queue.enqueue(t);
            } else if(t.equals("(")){
                stack.push(t);
            } else if(t.equals(")")){
                while(!stack.isEmpty() && !stack.peek().equals("(")){
                    queue.enqueue(stack.pop());
                }
                if(stack.peek().equals("(")){
                    stack.pop();
                }
            } else {
                while(!stack.isEmpty() && order(stack.peek()) >= order(t)){
                    queue.enqueue(stack.pop());
                }
                stack.push(t);
            }
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (String token : queue) {
            sb.append(token).append(" ");
        }
        return sb.toString().trim();
    }   
}
