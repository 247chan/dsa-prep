package challenges.arrays;

// LeetCode - Remove Duplicates from Sorted Array
// Given an integer array sorted in non-decreasing order, remove the duplicate in-place such that each unique element appears only once.
// The relative order of the elements should be kept the same.
// Then return the number of unique elements in nums.
public class RemoveDupSortedArray {
    public static int remove_duplicate(int[] nums){
        if(nums.length == 0) return 0;

        int len = nums.length;
        int[] newArray = new int[len];
        newArray[0] = nums[0];
        int idx = 0;

        for(int i = 1; i < len; i++){
            if(nums[i] == nums[i-1]){
                continue;
            } else {
                newArray[++idx] = nums[i];
            }
        }

        // for(int v: newArray){
        //     System.out.print(v + " ");
        // }

        return idx + 1;
    }
    public static void main(String[] args) {
        int[] array = {1,1,2};
        // int[] array = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(remove_duplicate(array));
    }
}
