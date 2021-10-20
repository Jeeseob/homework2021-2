public class PepperoniTopping extends AbstractTopping{

    private AbstractPizza pizza;

    public PepperoniTopping(AbstractPizza pizza) {
        super("pepperoni topping");
        this.pizza = pizza;
    }
    @Override
    public String toString(){return pizza.toString()+", "+super.toString();}
    @Override
    public double weight() {
        return pizza.weight() + 25;
    }
}
