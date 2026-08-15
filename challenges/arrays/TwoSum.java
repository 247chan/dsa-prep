package challenges.arrays;

// Question
// Find indices of two numbers that add up to a target number
// Example: target number = 26 -------> return 1 and 3
// because arr[1] + arr[3] = 26
public class TwoSum {
    public static int[] sumTwoValues(int[] arr, int sum){
        int[] result = {-1, -1};

        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] + arr[j] == sum){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 7, 15};
        int[] result = sumTwoValues(arr, 26);
        for(int i: result){
            System.out.print(i + " ");
        }
    }
}
