public class AgeSorter extends Sorter {
    @Override
    Boolean compare(Person p1, Person p2) {
        if(p1.getAge() > p2.getAge())
            return true;
        return false;
    }
}