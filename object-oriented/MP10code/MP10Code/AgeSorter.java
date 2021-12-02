public class AgeSorter extends BubbleSorter {
	public void bubbleSort(Person[] data) {
        sort(data);
    }

    public int compareTo(Person a, Person b) {
//        if (a.getAge() > b.getAge()) return 1;
//        else if (a.getAge() < b.getAge()) return -1;
//        else return 0;
        return a.getAge() - b.getAge();
    }
}
