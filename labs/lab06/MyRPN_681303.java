package labs.lab06;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class MyRPN_681303 {
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String strNum) {
        if (strNum == null)
            return false;
        return pattern.matcher(strNum).matches();
    }
    public static double computeRPN(String rpn) {
        MyStack_681303<Double> stack = new MyStack_681303<>();
        StringTokenizer st = new StringTokenizer(rpn);
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if(isNumeric(t)){
                stack.push(Double.parseDouble(t));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                double result = 0;
                switch (t) {
                    case "+":
                        result = a + b;
                        break;
                
                    case "-":
                        result = a - b;
                        break;
                    
                    case "*":
                        result = a * b;
                        break;

                    case "/":
                        result = a / b;
                        break;
                
                    default:
                        break;
                }
                stack.push(result);
            }

        } //while
        return stack.pop();
    }    
}
