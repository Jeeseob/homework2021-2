public class BubbleSorter {
    public static void sort(Person[] data, BubbleSorterComparable comparable) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (comparable.compareTo(data[j], data[j + 1]) > 0) {
                    Person temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}
