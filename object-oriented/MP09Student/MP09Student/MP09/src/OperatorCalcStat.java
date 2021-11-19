public class OperatorCalcStat implements Calcv2Stat{

    private CalcV2 calcV2;

    public OperatorCalcStat(CalcV2 calcV2) {
        this.calcV2 = calcV2;
    }
    @Override
    public void processNumber(String ch) {
        calcV2.setOperand2(Integer.parseInt(ch));
        calcV2.setCalcStat(new SecondOperandCalcStat(calcV2));
    }

    @Override
    public void processOperator(char ch) {

    }
}
