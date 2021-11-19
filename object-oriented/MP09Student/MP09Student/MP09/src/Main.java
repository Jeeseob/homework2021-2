public class Main {
    public static void main(String[] args) {
        //CalcV2 calc = new CalcV1();
        CalcV2 calc = new CalcV2();
        boolean run = true;
        while (run) {
            run = calc.run();
        }
    }
}
