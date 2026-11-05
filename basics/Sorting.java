package basics;

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
}
