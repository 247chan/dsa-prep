/**
 * LinkedListStack
 */

public class LinkedListStack {
    private Node top;

    private class Node{
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // push at front
    public void push(int val){
        Node newNode = new Node(val);
        newNode.next = top;
        top = newNode;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack empty");
        }
        int val = top.data;
        top = top.next;
        return val;
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("Stack empty");
        }
        return top.data;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public static void main(String[] args) {
        System.out.println("Stack with Linked List");
        System.out.println("*************************");
    }
}