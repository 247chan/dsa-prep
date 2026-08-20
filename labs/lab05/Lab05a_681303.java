package labs.lab05;

import java.util.Stack;

public class Lab05a_681303{
    public boolean backspaceCompare(String s, String t){
        return buildString(s).equals(buildString(t));
    }

    public String buildString(String str){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i <str.length(); i++){
            char c = str.charAt(i);

            if(c == '#'){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return String.valueOf(stack);
    }

    public static void main(String[] args){
        Lab05a_681303 sol = new Lab05a_681303();
        System.out.println(sol.backspaceCompare("ab#c", "ad#c"));
        System.out.println(sol.backspaceCompare("ab##", "c#d#"));
        System.out.println(sol.backspaceCompare("a#c", "b"));
    }
}

