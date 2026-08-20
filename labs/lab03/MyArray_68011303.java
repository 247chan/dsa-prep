package labs.lab03;

public class MyArray_68011303 extends MyArrayBasic_68011303 {
    public MyArray_68011303(int capacity) {
        MAX_SIZE = capacity > 0 ? capacity : 1;
        data = new int[MAX_SIZE];
    }

    public MyArray_68011303() {
        MAX_SIZE = 100_000;
        data = new int[MAX_SIZE];
    }
    
    public boolean isFull(){
        return size == MAX_SIZE;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void expandByK(int k){
        MAX_SIZE = k * MAX_SIZE;
        int[] newData = new int[MAX_SIZE];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void expandByK(){
        expandByK(2);
    }

    @Override
    public int add(int d){
        if(isFull()){
            expandByK();
        }
        data[size++] = d;
        return size;
    }

    @Override
    public void insert_keep_ordered(int index, int d){
        if(isFull()) expandByK();
        super.insert_keep_ordered(index, d);
    }

    @Override
    public void delete(int index){
        if(!isEmpty()){
            super.delete(index);
        }
    }

    public int binarySearch_lb(int target){
        int low = 0;
        int high = size;
        int mid;

        while (low < high){
            mid = low + (high - low) / 2;

            if(target <= data[mid]){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return -(low+1);
    }
}
