public class FirstOperandCalcStat implements Calcv2Stat {
    private CalcV2 calcV2;

    public FirstOperandCalcStat(CalcV2 calcV2) {
        this.calcV2 = calcV2;
    }

    @Override
    public void processNumber(String ch) {

    }

    @Override
    public void processOperator(char ch) {
        calcV2.setOperator(ch);
        calcV2.setCalcStat(new OperatorCalcStat(calcV2));
    }
}
