public class NameSorter extends BubbleSorter implements BubbleSorterComparable {
    public void bubbleSort(Person[] data) {
        BubbleSorter.sort(data, this);
    }

    public int compareTo(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}
