public class AgeSorter implements BubbleSorterComparable {
	public void bubbleSort(Person[] data) {
        BubbleSorter.sort(data, this);
    }

    public int compareTo(Person a, Person b) {
//        if (a.getAge() > b.getAge()) return 1;
//        else if (a.getAge() < b.getAge()) return -1;
//        else return 0;
        return a.getAge() - b.getAge();
    }
}
