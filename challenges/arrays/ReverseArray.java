package challenges.arrays;

// Question
// Reverse an array;
public class ReverseArray {
    static void reverseArr(int[] arr){
        int l = 0;
        int r = arr.length - 1;
        while(l <= r){
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp; 
            l++;
            r--;
        }

        System.out.println("After reversing\n");
        for(int v: arr){
            System.out.print(v + " ");
        }
    }
    public static void main(String[] args) {
        int[] arr = {11,22,33,44,55};
        reverseArr(arr);
    }
    
}
