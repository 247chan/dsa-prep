package challenges.stacks;

import java.util.Stack;

public class BalancedParentheses {
    public static boolean checkBalanced(String s){
        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            } else if(c == ')' || c == '}' || c == ']'){
                if(stack.isEmpty()){
                    return false;
                }
                char open = stack.pop();
                if((c == ')' && open != '(') ||
                (c == '}' && open != '{') ||
                (c == '[' && open != '[')){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkBalanced("{}"));
        System.out.println(checkBalanced("()[]{}"));
        System.out.println(checkBalanced("[}"));
    }
}