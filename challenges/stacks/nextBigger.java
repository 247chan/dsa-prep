package challenges.stacks;

import java.util.*;

// Question
// For each number in the array, find the next number to its right that is bigger
// {4,5,2,10,8} -> {5,10,10,-1,-1}
public class nextBigger {
    public static int[] nextBiggerElement(int[] arr){
        int[] result = new int[arr.length];
        Arrays.fill(result, -1); // if no bigger element found
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = nextBiggerElement(arr);

        for(int i: result){
            System.out.print(i + " ");
        }
    }
    
}
