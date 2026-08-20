package labs.lab06;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyQueueL_681303<T> implements Iterable<T> {
    private List<T> items = new LinkedList<>(); // remove(0) is O(1)

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("top->");
        for (T item : items)
            sb.append(item).append("-> ");
        sb.append("bottom");
        return sb.toString();
    }

    public void enqueue(T data) {
        items.add(data);
    }

    public T dequeue() {
        if(!isEmpty())
            return items.remove(0);
        return null;
    }

    public T peek(){
        if(!isEmpty()){
            return items.get(0);
        }
        return null;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    @Override
    // so that one can use for-each on MyQueueL
    public Iterator<T> iterator() {
        return items.iterator();
    }       
}
