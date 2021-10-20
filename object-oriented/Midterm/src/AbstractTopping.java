public abstract class AbstractTopping extends AbstractPizza{

    String toppingName;

    public AbstractTopping(String name) {
        toppingName = name;
    }
    @Override
    public  String toString(){
        return toppingName;
    }
}
