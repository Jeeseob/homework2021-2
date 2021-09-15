public class CompareSize implements Comparable{

    public CompareSize() {
        super();
    }
    @Override
    public int compareTo(FileInfo file1, FileInfo file2) {
        if (file1.getSize() > file2.getSize()) {
            return 1;
        } else {
            return 0;
        }
    }
}
