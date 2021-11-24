public abstract class Sorter {

    public final void bubbleSort(Person[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if(compare(data[j], data[j+1])) {
                    Person temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }

            }
        }
    }
    abstract Boolean compare(Person p1, Person p2);
}
