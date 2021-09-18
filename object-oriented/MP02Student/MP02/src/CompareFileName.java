public class CompareFileName implements Comparable{

    public CompareFileName() {
        super();
    }
    @Override
    public int compareTo(Object o1, Object o2) {
            FileInfo file1 = (FileInfo) o1;
            FileInfo file2 = (FileInfo) o2;
        if (file1.getName().compareTo(file2.getName()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
