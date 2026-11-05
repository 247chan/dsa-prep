package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorting {
    // ---------- O(n^2) comparison sorts ----------
    static void bubbleSort(int[] a) {
        int n = a.length;
        int temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swapped = true;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            if (!swapped) // best case O(n)
                break;
        }
    }

    static void selectionSort(int[] a) {
        int n = a.length;
        int temp, min;
        for (int i = 0; i < n - 1; i++) {
            min = i;

            // loop for finding the smallest ele in the rest of the array
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) { // u have to compare with a[min]
                    min = j;
                }
            }

            temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

    // insertion sort shift elements, not swapping
    static void insertionSort(int[] a) {
        int n = a.length;
        // insertion sort starts from i 1
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;

            // shift element one place to right if they are larger
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--; // move to j - 1 to check the next
            }

            // after the loop, j + 1 is the exact open slot where key belongs.
            a[j + 1] = key;
        }
    }

    static void shellSort(int[] a) { // insertion sort with shrinking gap
        int n = a.length;
        for (int gap = n / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < n; i++) {
                int key = a[i];
                int j = i;

                while (j >= gap && a[j - gap] > key) { // compare with the ele 'gap' places back
                    a[j] = a[j - gap]; // shift it forward by gap
                    j -= gap; // move pointer j by gap
                }

                a[j] = key; // drop the key in place
            }
        }
    }

    // --------------- non-comparison sorts ---------------

    // count how many times each value appear
    // use those counts to find where each ele belong in the output
    static int[] countingSort(int[] a) { // works for negatives too (offset by min)
        // nothing to sort
        if (a.length == 0)
            return a;

        // Find smallest & largest ele
        int min = a[0];
        int max = a[0];
        for (int x : a) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        int[] count = new int[max - min + 1];

        for (int x : a)
            count[x - min]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1]; // prefix sum = end position

        int[] out = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) // right-to-left => STABLE
            out[--count[a[i] - min]] = a[i];
        return out;
    }

    static void radixSort(int[] a) {
        if (a.length == 0)
            return;

        // Finding max ele in the array
        // stream - max - getAsInt
        int max = Arrays.stream(a).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) { // one counting-sort pass per digit
            int[] count = new int[10]; // 10 because digit can be 0 to 9;
            int[] out = new int[a.length];

            // counting sort
            for (int x : a)
                count[(x / exp) % 10]++; // (x / exp) % 10 pulls out one digit
            for (int i = 1; i < 10; i++)
                count[i] += count[i - 1];
            for (int i = a.length - 1; i >= 0; i--) {
                int d = (a[i] / exp) % 10;
                out[--count[d]] = a[i];
            }

            // copy result back into array 'a' for the next pass
            // args: (src, srcPos, dest, destPos, length);
            System.arraycopy(out, 0, a, 0, a.length);
        }
    }

    // lecture: keys in [0..max] (counting-style buckets)
    static void bucketSortInts(int[] a, int maxValue) {
        int[] bucket = new int[maxValue + 1];
        for (int x : a) {
            bucket[x]++;
        }

        int k = 0; // next write position in array 'a'
        for (int v = 0; v <= maxValue; v++) {
            while (bucket[v] > 0) { // read the buckets in order
                a[k] = v; // write v as many times as it was counted
                k++;
                bucket[v]--;
            }
        }
    }

    static void bucketSortDoubles(double[] a) { // values in [0,1): n buckets, sort each, concatenate
        int n = a.length;
        List<List<Double>> b = new ArrayList<>();

        // n empty buckets in b [ [], [], [], [] ]
        for (int i = 0; i < n; i++)
            b.add(new ArrayList<>());

        // bucket index is x * n
        // cast it to int for index
        for (double x : a)
            b.get((int) (x * n)).add(x);

        int k = 0;
        for (List<Double> l : b) {
            Collections.sort(l); // sort each bucket using Collections.sort
            for (double x : l) // write them back to a
                a[k++] = x;
        }
    }

}
