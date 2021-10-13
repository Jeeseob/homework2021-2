import java.util.Iterator;

public class IntDynamicArray implements Iterable {
    private final int INCREMENT_SIZE = 10;
    // 현재 들어있는 데이터의 양
    private int count;
    // 현재 array의 사이즈
    private int size;
    // 데이터가 들어갈 int형 배열
    private int[] arr;

    public IntDynamicArray() {
        // 선언부 (초기화)
        arr = new int[INCREMENT_SIZE];
        size = INCREMENT_SIZE;
        count = 0;
    }

    public void add(int n) {
        if (count >= size) {
            // 현재 데이터의 양과 가능한 사이즈를 비교하여 공간이 부족할 경우, 공간 추가.
            // 임시로 arr2를 만든 후 data 복사 후 arr2를 arr로 변경.
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
            // 인덱스 초과 오류 반환.
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
