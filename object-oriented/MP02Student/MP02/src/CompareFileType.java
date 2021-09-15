public class CompareFileType implements Comparable{

    public CompareFileType() {
            super();
        }
    @Override
    public int compareTo(FileInfo file1, FileInfo file2) {
        if (file1.getType().compareTo(file2.getType()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
