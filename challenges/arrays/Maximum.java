package challenges.arrays;

// Question
// Find the maximum ele in a n array
public class Maximum {
    static void findMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        System.out.println("Max is " + max);
    }
    public static void main(String[] args) {
        int[] arr = {5, 12, 5, 1, -2, 65, 7};
        findMax(arr);
    }
}
