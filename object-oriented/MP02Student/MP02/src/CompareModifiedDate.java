public class CompareModifiedDate implements Comparable{

    public CompareModifiedDate() {
        super();
    }
    @Override
    public int compareTo(FileInfo file1, FileInfo file2) {
        if (file1.getModifiedDate().compareTo(file2.getModifiedDate()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
