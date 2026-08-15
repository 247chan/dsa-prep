package challenges.arrays;

// Leetcode - Sort Colors
// Given an array nums with n objects colored red, white, blue
// Sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
// Use integers 0, 1, 2 to represent colors.
// Example: input: nums = {2,0,2,1,1,0} ------> output: {0,0,1,1,2,2}
// Solve this problem without using library sort function.

public class SortColors {
    public static void sortColorArray(int[] array){
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        int temp = 0;

        while(mid <= high){
            if(array[mid] == 0){
                temp = array[low];
                array[low] = array[mid];
                array[mid] = temp;
                low++;
                mid++;
            } else if(array[mid] == 1){
                mid++;
            } else {
                temp = array[mid];
                array[mid] = array[high];
                array[high] = temp;
                high--;
            }
        }
    }
    public static void main(String[] args) {
        int[] array = {2,0,2,1,1,0};
        sortColorArray(array);
    }
}
