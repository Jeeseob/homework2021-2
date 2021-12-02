public abstract class BubbleSorter implements BubbleSorterComparable {
    public void sort(Person[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (compareTo(data[j], data[j + 1]) > 0) {
                    Person temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public abstract int compareTo(Person a, Person b);
}
