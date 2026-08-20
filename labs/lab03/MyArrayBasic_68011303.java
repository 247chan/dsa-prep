package labs.lab03;

public class MyArrayBasic_68011303 {
    protected int MAX_SIZE = 5;
    protected int data[] = new int[MAX_SIZE];
    protected int size = 0;

    public MyArrayBasic_68011303(int ...a) {
        data = new int[MAX_SIZE];
        int len = a.length < MAX_SIZE ? a.length : MAX_SIZE;
        for(int i = 0; i < len; i++){
            data[i] = a[i];
        }
        size = len;
    }

    public int add(int d){
        if(size == MAX_SIZE) {
            return size;
        }
        data[size++] = d;
        return size;
    }

    public void insert_keep_ordered(int index, int d){
        for(int i = size; i > index; i--){
            data[i] = data[i - 1];
        }
        data[index] = d;
        size++;
    }

    public int find(int d){
        for(int i = 0; i < size; i++){
            if(data[i] == d){
                return i;
            }
        }
        return -1;
    }

    public void delete(int index){
        for(int i = index; i < size; i++){
            data[i] = data[i + 1];
        }
        if(size > 0) size--;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < size - 1; i++)
            sb.append(data[i]+", ");
        if (size > 0) sb.append(data[size-1]);
        sb.append("]");
        return sb.toString();
    }

    public int getAt(int idx) {
        return data[idx];
    }

    public void setAt(int d, int idx) {
        data[idx] = d;
    }
}
