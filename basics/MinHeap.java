package basics;

import java.util.*;

// ---------- array-based MIN heap (lecture layout: root at 0, parent=(i-1)/2, children 2i+1, 2i+2) ----------
// root: 0
// current: i
// left child: 2i+1
// right child: 2i+2
// parent: (i-1)/2
public class MinHeap {
    int[] heap = new int[100];
    int size = 0;

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == heap.length;
    }

    int peek() {
        return heap[0];
    }

    void swap(int a, int b) {
        int t = heap[a];
        heap[a] = heap[b];
        heap[b] = t;
    }

    void insert(int d) { // O(log n): add at the end, heapify_down
        if (isFull())
            heap = Arrays.copyOf(heap, heap.length * 2);

        int p = size++;
        heap[p] = d;
        while (p > 0 && heap[p] < heap[(p - 1) / 2]) {
            swap(p, (p - 1) / 2);
            p = (p - 1) / 2;
        }
    }

    int remove() { // O(log n): move last to root, heapify_down
        int d = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return d;
    }

    void siftDown(int p) {
        while (true) {
            int l = 2 * p + 1, r = l + 1, smallest = p;
            if (l < size && heap[l] < heap[smallest])
                smallest = l;
            if (r < size && heap[r] < heap[smallest])
                smallest = r;
            if (smallest == p)
                break;
            swap(p, smallest);
            p = smallest;
        }
    }

    static MinHeap heapify(int[] a) { // O(n): sift down from last parent to root
        MinHeap h = new MinHeap();
        h.heap = Arrays.copyOf(a, Math.max(a.length, 1));
        h.size = a.length;
        // i starts from last non-leaf node
        // last non-leaf node = n/2 - 1 (take floor)
        for (int i = h.size / 2 - 1; i >= 0; i--)
            h.siftDown(i);
        return h;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(heap, size));
    }

    // ---------- heap sort (ascending) ----------
    static int[] heapSort(int[] a) {
        MinHeap h = MinHeap.heapify(a);
        int[] out = new int[a.length];
        for (int i = 0; i < out.length; i++)
            out[i] = h.remove();
        return out;
    }

    // in-place heap sort with a MAX heap (O(1) extra space)
    static void heapSortInPlace(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            siftDownMax(a, i, n);
        for (int end = n - 1; end > 0; end--) {
            int t = a[0];
            a[0] = a[end];
            a[end] = t; // biggest goes to the back
            siftDownMax(a, 0, end);
        }
    }

    static void siftDownMax(int[] a, int p, int n) {
        while (true) {
            int l = 2 * p + 1, r = l + 1, big = p;
            if (l < n && a[l] > a[big])
                big = l;
            if (r < n && a[r] > a[big])
                big = r;
            if (big == p)
                return;
            int t = a[p];
            a[p] = a[big];
            a[big] = t;
            p = big;
        }
    }
}

// // ---------- wrapper: priority queue ADT (lecture MyPQueue) ----------
// static class MyPQueue {
// MinHeap heap = new MinHeap();

// void enqueue(int d) {
// heap.insert(d);
// }

// int dequeue() {
// return heap.remove();
// }

// int front() {
// return heap.peek();
// }

// boolean isEmpty() {
// return heap.isEmpty();
// }
// }