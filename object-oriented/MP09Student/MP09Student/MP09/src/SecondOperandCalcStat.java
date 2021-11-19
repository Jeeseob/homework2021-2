public class SecondOperandCalcStat implements Calcv2Stat{
    private CalcV2 calcV2;

    public SecondOperandCalcStat(CalcV2 calcV2) {
        this.calcV2 = calcV2;
    }

    @Override
    public void processNumber(String ch) {

    }

    @Override
    public void processOperator(char ch) {
        calcV2.printOutResult();
        calcV2.setCalcStat(new StartCalcStat(calcV2));
    }
}
