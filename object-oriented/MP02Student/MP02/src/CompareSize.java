public class CompareSize implements Comparable{

    public CompareSize() {
        super();
    }
    @Override
    public int compareTo(Object o1, Object o2) {
        FileInfo file1 = (FileInfo) o1;
        FileInfo file2 = (FileInfo) o2;
        if (file1.getSize() > file2.getSize()) {
            return 1;
        } else {
            return 0;
        }
    }
}
