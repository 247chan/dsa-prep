package pack;

public class MyPriorityQueue_681303 implements MyQueueIntf {
    MyMinHeap_681303 heap = new MyMinHeap_681303();
    
    @Override
    public void enqueue(int d) {
        if(isFull()){
            return;
        }
        heap.insert(d);
    }

    @Override
    public int dequeue() {
        if(isEmpty()){
            throw new IllegalStateException("queue is empty");
        }
        return heap.remove();
    }

    @Override
    public int front() {
        if(isEmpty()){
            throw new IllegalStateException("queue is empty");
        }
        return heap.peek();
    }

    @Override
    public boolean isFull() {
        return heap.isFull();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }    

    @Override
    public String toString() {
        return heap.toString();
    }
}
