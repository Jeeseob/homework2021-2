import java.util.Iterator;

public class IntDynamicArray implements Iterable {
    final int INCREMENT_SIZE = 10;
    int count;
    int size;
    private int[] arr;

    public IntDynamicArray() {
        arr = new int[INCREMENT_SIZE];
        size = INCREMENT_SIZE;
        count = 0;
    }

    public void add(int n) {
        if (count >= size) {
            int[] arr2 = new int[size + INCREMENT_SIZE];
            for (int i = 0; i < size; i++) {
                arr2[i] = arr[i];
            }
            size += INCREMENT_SIZE;
            arr = arr2;
        }
        arr[count] = n;
        count++;
    }

    public int get(int idx) throws ArrayIndexOutOfBoundsException {
        if (idx < size) {
            return arr[idx];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return count;
    }

    public Iterator iterator() {
        return new IntDynamicArrayIterator(this);
    }
}
