package pack;

public class MyMinHeap_681303 {
    int MAX_SIZE = 100;
    int heap[] = new int[MAX_SIZE];
    int size = 0;
    
    private void swap(int i, int j) {
        heap[i] = heap[i] + heap[j];
        heap[j] = heap[i] - heap[j];
        heap[i] = heap[i] - heap[j];
    }
    public int peek() {
        return heap[0];
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int d) {
        if(isFull()){
            return;
        }

        int c = size++;
        heap[c] = d;

        while(c > 0){
            int parent = (c - 1)/2;
            if(heap[parent] > heap[c]){
                swap(parent, c);
                c = parent;
            } else {
                break;
            }
        }
    }

    public int remove() {
        int d = heap[0];
        heap[0] = heap[--size];
        heap[size] = d;
        int p = 0;
        while (true) {
            int left = 2 * p + 1;
            int right = 2 * p + 2;
            int smallest = p;

            if(left < size && heap[left] < heap[smallest]){
                smallest = left;
            }
            if(right < size && heap[right] < heap[smallest]){
                smallest = right;
            }
            if(smallest == p){
                break;
            }

            swap(p, smallest);
            p = smallest;

            System.out.println("heap snapshot (cur_size = " + size + ") " + this.toString());
        } 
        return d;
    }

    public String toString() {
        int i;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (i = 0; i < size; i++) {
            sb.append(heap[i]);
            sb.append(",");
        }
        if (size > 0) {
            sb.append("]");
        }
        return sb.toString();
    }
    
}
