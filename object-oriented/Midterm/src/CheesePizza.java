public class CheesePizza extends AbstractPizza{

    @Override
    public double weight() {
        return 280;
    }
    @Override
    public String toString() {
        return super.toString()+"CheesePizza";
    }
}
