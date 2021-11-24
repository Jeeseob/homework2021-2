public class NameSorter extends Sorter {
    @Override
    Boolean compare(Person p1, Person p2) {
        if(p1.getName().compareTo(p2.getName()) >0)
            return true;
        return false;
    }
}

