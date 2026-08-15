package challenges;

import java.util.*;
import java.util.regex.Pattern;

public class ShuntingYard {
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String s){
        if(s == null) return false;

        return pattern.matcher(s).matches();
    }

    public static int op_order(String op){
        switch (op) {
            case "*", "/":
                return 2;

            case "+", "-":
                return 1;
        
            default:
                return 0;
        }
        
    }

    public static String infixToPostfix(String s){
        StringBuilder output = new StringBuilder();
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(s);
        while(st.hasMoreTokens()){
            String t = st.nextToken();

            if(isNumeric(t)){
                queue.add(t);
            } else if(t.equals("(")){
                stack.push(t);
            } else if(t.equals(")")){
                while(!stack.isEmpty() && !stack.peek().equals("(")){
                    queue.add(stack.pop());
                }
                if(!stack.isEmpty() && stack.peek().equals("(")){
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else {
                while(!stack.isEmpty() && op_order(stack.peek()) >= op_order(t)){
                    queue.add(stack.pop());
                }
                stack.push(t);
            }  
        }

        while(!stack.isEmpty()){
            String top = stack.pop();
            if (top.equals("(") || top.equals(")")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            queue.add(top);
        }

        for(String sb: queue){
            output.append(sb).append(' ');
        }

        return output.toString().trim();

    }
    public static void main(String[] args) {
        System.out.println(infixToPostfix("3 + 4 * 2"));         // 3 4 2 * +
        System.out.println(infixToPostfix("( 3 + 4 ) * 2"));       // 3 4 + 2 *
        System.out.println(infixToPostfix("10 + 2 * 6 - 3"));    // 10 2 6 * + 3 -
    }
}
