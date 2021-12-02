public class NameSorter extends BubbleSorter {
    public void bubbleSort(Person[] data) {
        sort(data);
    }

    public int compareTo(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}
