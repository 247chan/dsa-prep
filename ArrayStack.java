/**
 * ArrayStack
 */
public class ArrayStack {
    private int[] data;
    private int top;

    public ArrayStack(int capacity){
        data = new int[capacity];
        top = -1;
    }

    public void push(int val){
        if(top == data.length - 1){
            throw new RuntimeException("Stack overflow");
        }
        data[++top] = val;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack Underflow");
        }
        return data[top--];
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("Stack empty");
        }
        return data[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public static void main(String[] args) {
        System.out.println("Array Stack");
        System.out.println("***************");
    }
}