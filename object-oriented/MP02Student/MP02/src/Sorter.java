public class Sorter {
    private Comparable comparable;

    public Sorter(Comparable comparable) {
        this.comparable = comparable;
    }

    public void setComparable(Comparable comparable) {
        this.comparable = comparable;
    }

    public void bubbleSort(FileInfo[] data) {
        for (int i = 0; i < data.length -1; i++) {
            for (int j = 0; j < data.length-i-1; j++) {
                if (comparable.compareTo(data[j], data[j+1]) == 1) {
                    FileInfo temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}
