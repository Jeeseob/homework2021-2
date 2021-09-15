public class CompareFileName implements Comparable{

    public CompareFileName() {
        super();
    }
    @Override
    public int compareTo(FileInfo file1, FileInfo file2) {
        if (file1.getName().compareTo(file2.getName()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
