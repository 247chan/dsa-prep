package labs.lab03;

// import java.util.Arrays;

public class Lab03_68011303 {
    static private void arrayBasic_demo_1() {
       MyArrayBasic_68011303 demo = new MyArrayBasic_68011303(7,6,8,1,2,3);
        System.out.println( demo );     // [7, 6, 8, 1, 2]
    }
    static private void arrayBasic_demo_2() {
        MyArrayBasic_68011303 demo = new MyArrayBasic_68011303();
        demo.insert_keep_ordered(0,9);
        demo.insert_keep_ordered(0,7);
        demo.insert_keep_ordered(0,5);
        System.out.println( demo );                    // [5, 7, 9]
        System.out.println("5 is at " + demo.find(5)); // 5 is at 0
        System.out.println( demo.getAt(1) );      // 7        
    }
    static void arrayBasic_demo_3() {
        MyArrayBasic_68011303 demo = new MyArrayBasic_68011303();
        demo.add(3);    
        demo.add(7);
        demo.add(5);    
        demo.add(4);
        demo.add(6);
        System.out.println("next add operation trickers ArrayIndexOutOfBoundsException");
        demo.add(1);
    }
    static void array_demo_4() {
       MyArray_68011303 demo = new MyArray_68011303(5);
        demo.delete(0);
        demo.add(3);    
        demo.add(7);
        demo.add(5);    
        demo.add(4);
        demo.add(6);
        demo.add(1);
        System.out.println( demo ); // [3, 7, 5, 4, 6, 1]
    }
static void array_demo_5() {
   MyArray_68011303 demo = new MyArray_68011303();
    demo.insert_keep_ordered(0,9);
    demo.insert_keep_ordered(0,8); 
    demo.insert_keep_ordered(0,7); 
    demo.insert_keep_ordered(0,5);
    System.out.println(demo );                              // [5, 7, 8, 9]
    System.out.println("5 is at " + demo.binarySearch_lb(5)); // return -1 bec. insertion pos for another 5 would be (-1 +1 ) * -1 = 0
    System.out.println("4 is at " + demo.binarySearch_lb(4)); // return -1 bec. insertion pos for 4 would be (-1 +1 ) * -1 = 0
    int pos = demo.binarySearch_lb(6);
    System.out.println("6 is at " + pos);       // retrurn -2 bec. insertion pos for 6 is (-2 +1) * -1 = 1
    demo.insert_keep_ordered(-(pos+1),6);       //[5, 6, 7, 8, 9]
    System.out.println( demo );
}
    static void array_demo_6() {
        System.out.println("small size initialized");
        for (int N = 200_000; N <= 10 * 200_000; N += 200_000) {
            long start = System.currentTimeMillis();
           MyArray_68011303 mArray = new MyArray_68011303(200_000/40_000);
            for (int n = 1; n < N; n++)
                mArray.add((int)(Math.random()*1000));
            long time = System.currentTimeMillis() - start;
            System.out.println(N + "\t\t" + time);        
        }
        System.out.println("large size initialized");
        for (int N = 200_000; N <= 10 * 200_000; N += 200_000) {
            long start = System.currentTimeMillis(); // capacity = 100_000
           MyArray_68011303 mArray = new MyArray_68011303();
            for (int n = 1; n < N; n++)
                mArray.add((int)(Math.random()*1000));
            long time = System.currentTimeMillis() - start;
            System.out.println(N + "\t\t" + time);        
        }
    }

    public static void main(String[] args) { 
        System.out.println("test pack structure");
        System.out.println("calling demo_1");
        arrayBasic_demo_1();
        System.out.println("calling demo_2");
        arrayBasic_demo_2();
        System.out.println("invoke as last method only, it will crash the main \ncalling demo_3");
        arrayBasic_demo_3();
        System.out.println("calling demo_4");
        array_demo_4();
        System.out.println("calling demo_5");
        array_demo_5();
        System.out.println("calling demo_6");
        array_demo_6();
        taskA();
    } 
    static class Solution {
        int removeDuplicates(int[] nums) {
            int l = 1, r = 1;
            for(r = 1; r < nums.length; r++){
                if(nums[r] != nums[l - 1]){
                    nums[l] = nums[r];
                    l++;
                }
            }
            return l;
        }
    }
    static void taskA() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        Solution sol = new Solution();
        System.out.println( sol.removeDuplicates(nums)); // 5
    }

}
