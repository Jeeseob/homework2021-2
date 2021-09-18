public class CompareModifiedDate implements Comparable{

    public CompareModifiedDate() {
        super();
    }
    @Override
    public int compareTo(Object o1, Object o2) {
        FileInfo file1 = (FileInfo) o1;
        FileInfo file2 = (FileInfo) o2;
        if (file1.getModifiedDate().compareTo(file2.getModifiedDate()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
