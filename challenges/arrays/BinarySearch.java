package challenges.arrays;

// Question
// Implement a binary search
public class BinarySearch {
    public static int do_binary_search(int[] array, int val){
        int a = 0;
        int b = array.length - 1;
        while(a <= b){
            int idx = (a + b) / 2;
            if(val == array[idx]){
                return idx;
            } else if(val < array[idx]){
                b = idx - 1;
            } else {
                a = idx + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(do_binary_search(array, 5));
        System.out.println(do_binary_search(array, 10));
        System.out.println(do_binary_search(array, 1));
    }
}
