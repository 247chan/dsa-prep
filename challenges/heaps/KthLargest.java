package challenges.heaps;

import java.util.PriorityQueue;

public class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;

    KthLargest(int k, int[] init) {
        this.k = k;

        for (int x : init)
            add(x);
    }

    int add(int s) {
        pq.offer(s);
        if (pq.size() > k)
            pq.poll();

        return pq.peek();
    }

}
