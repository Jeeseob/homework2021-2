public class OnionTopping extends AbstractTopping{

    private AbstractPizza pizza;

    public OnionTopping(AbstractPizza pizza) {
        super("onion topping");
        this.pizza = pizza;
    }

    @Override
    public String toString(){
        return pizza.toString()+", "+super.toString();
    }
    @Override
    public double weight() {
        return pizza.weight() + 30;
    }
}
