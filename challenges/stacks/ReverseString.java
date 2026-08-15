package challenges.stacks;

import java.util.*;

public class ReverseString {
    public static String reverseString(String s){
        Stack<String> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(s);

        while(st.hasMoreTokens()){
            String t = st.nextToken();
            stack.push(t);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        String s = "one two three four five";
        System.out.println(reverseString(s));
    }
    
}
