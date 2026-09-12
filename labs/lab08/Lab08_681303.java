import java.util.Arrays;

import pack.*;

public class Lab08_681303 {
    // int[] arr = {42,17,-5,88,23,91,-12,65,7,30,55,-9,2,48,76,1,-22,99,14,61,37,83,-18,50,29,72,6,40,-3,95,11,68};
    static void task_1() {
        int[] arr = {42,17,-5,88,23,91,-12,65,7,30,55,-9,2,48,76,1,-22,99,14,61,37,83,-18,50,29,72,6,40,11,68}; // -3, 95
        MyMergeSort_681303 sol = new MyMergeSort_681303();
        sol.mSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void task_2() {
        int[] arr = {42,17,-5,88,23,91,-12,65,7,30,55,-9,2,48,76,1,-22,99,14,61,37,83,-18,50,29,72,6,40,11,68}; // -3, 95
        MyQuickSort_681303 sol = new MyQuickSort_681303();
        sol.qSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void dutch_national_flag(int[] arr) {
        int left_value = arr[0];    // min
        int right_value = arr[0];   // max
        for (int i = 1; i < arr.length; i++) {
            left_value = Math.min(left_value, arr[i]);
            right_value = Math.max(right_value, arr[i]);
        }

        int low = 0;
        int mid = 0;
        int temp = 0;
        int high = arr.length - 1;

        while(mid <= high){

            if(arr[mid] == left_value){
                temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++; 
                mid++;
            } else if(arr[mid] == right_value){
                temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            } else {
                mid++;
            }
        }
    }
    static void task_3() {
        // int[] arr = {1,0,2,2,0,0,1,2,2,1,1,2,1,0,2};

                    // R  B  W  W  B  B  R  W  W  R  R  W  R  B  W
        int [] arr = {-1, 0,-2,-2, 0, 0,-1,-2,-2,-1,-1,-2,-1, 0,-2};
        dutch_national_flag(arr);
        System.out.println(Arrays.toString(arr));
    }
    static int k_th_min_element(int[] arr, int k) {
        int min = arr[0];    
        int max = arr[0];   
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        int[] count = new int[max - min + 1];

        for(int i = 0; i < arr.length; i++){
            count[arr[i] - min]++;
        }

        // checking the count array
        // for(int x = 0; x < count.length; x++){
        //     System.out.println(count[x]);
        // }

        int acc = 0;

        for(int i = 0; i < count.length; i++){
            acc += count[i];
            if(acc >= k){
                return i + min;
            }
        }

        return -1; // exception
    }
    static void task_4() {
        int[] arr = {-1,0,-2,-2,0,0,-1,-2,-2,-1,-1,-2,-1,0,-2};
        System.out.println(k_th_min_element(arr,2));        
    }
    public static void main(String[] args) {
        task_1();
        task_2();
        task_3();
        task_4();
    }
}