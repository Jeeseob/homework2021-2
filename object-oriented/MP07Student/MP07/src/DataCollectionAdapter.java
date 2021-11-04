import java.util.ArrayList;
import java.util.Iterator;

public class DataCollectionAdapter<T> implements DataCollection<T>{
    private ArrayList<T> arrayList;

    public DataCollectionAdapter() {
        arrayList = new ArrayList<T>();
    }

    @Override
    public boolean put(T t) {
        arrayList.add(t);
        return true;
    }

    @Override
    public T elemAt(int index) {
        return arrayList.get(index);
    }

    @Override
    public int length() {
        return arrayList.size();
    }

    @Override
    public Iterator createIterator() {
        Iterator<T> iterator = arrayList.iterator();
        return iterator;
    }
}
