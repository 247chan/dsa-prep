import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    // static method belongs to the class itself, Main.add(..)
    public static int add(int a, int b) {
        return a + b;
    }

    public static int sumArray(int[] array){
        int sum = 0;
        for(int val: array){
            sum += val;
        }
        return sum;
    }
    public static void main(String[] args){
        System.out.println("Hello JAVA!");   

        // for loop
        for(int i = 0; i < 10; i++){
            System.out.println(i);
        }

        // for each
        int[] array = {111, 222, 333, 444, 555};
        for(int val: array){
            System.out.println(val);
        }

        // sample function call
        System.out.println(add(5,3));

        // Array Declaring & creating
        int[] arr = new int[5];             // arry of 5 ints, all default 0
        int[] arr2 = {6, 7, 8, 9, 10};      //array literal
        String[] names = new String[3];
        int[][] grid = new int[3][5];       // 2D array, 3 rows, 5 cols
        
        int len = arr.length;               // get array length

        // Common array operations
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] copy = Arrays.copyOf(arr, arr.length);
        int[] slice = Arrays.copyOfRange(arr2, 1, 4);   // slice array eles at index 1 to 3
        Arrays.fill(arr, 5);                            // sets every element to 5

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };

        System.out.println("Printing Matrix");

        for(int r = 0; r < matrix.length; r++){
            for(int c = 0; c < matrix[r].length; c++){
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }

        System.out.println(sumArray(arr2));

        // Built-in Linked list
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.addFirst(0);
        list.addLast(2);
        list.add(3);
        list.add(4);
        list.remove(0);
        list.removeFirst();
        list.removeLast();
        int first = list.get(0);    // access by index
        list.contains(1);           // search 
        System.out.println(list);
    }
}