public class CompareFileType implements Comparable{

    public CompareFileType() {
            super();
        }
    @Override
    public int compareTo(Object o1, Object o2) {
            FileInfo file1 = (FileInfo) o1;
            FileInfo file2 = (FileInfo) o2;
        if (file1.getType().compareTo(file2.getType()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
