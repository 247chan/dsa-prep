package challenges.stacks;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int val){
        stack.push(val);
        if(minStack.isEmpty() || minStack.peek() >= val){
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop(){
        stack.pop();
        return minStack.pop();
    }

    public int getMin(){
        return minStack.peek();
    }
    public static void main(String[] args) {
        MinStack ms = new MinStack();

        ms.push(5);
        ms.push(3);
        ms.push(7);
        System.out.println("Min after pushing 5,3,7: " + ms.getMin()); // Expected: 3

        ms.pop(); // removes 7
        System.out.println("Min after popping 7: " + ms.getMin()); // Expected: 3

        ms.pop(); // removes 3
        System.out.println("Min after popping 3: " + ms.getMin()); // Expected: 5

        ms.push(2);
        ms.push(8);
        System.out.println("Min after pushing 2,8: " + ms.getMin()); // Expected: 2

        ms.pop(); // removes 8
        System.out.println("Min after popping 8: " + ms.getMin()); // Expected: 2
    } 
}
