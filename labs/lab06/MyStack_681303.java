package labs.lab06;

import java.util.ArrayList;


public class MyStack_681303<T> { 
    private ArrayList<T> items
        = new ArrayList<>(); //// extends would expose MyStack object for calling any of the 

    public void push(T data) {
        items.add(data);
    }

    public T pop() {
        if(!isEmpty()){
            return items.remove(items.size() - 1);
        }
        return null;
    }

    public T peek(){
        if(!isEmpty()){
            return items.get(items.size() - 1);
        }
        return null;
    }

    public int size(){
        return items.size();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = items.size() - 1; i > 0; i--)
            sb.append(items.get(i) + ", "); 
        if (items.size() > 0)
            sb.append(items.get(0));
        sb.append("]");
        return sb.toString();
    }
}
