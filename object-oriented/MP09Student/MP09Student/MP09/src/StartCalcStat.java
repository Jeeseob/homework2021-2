public class StartCalcStat implements Calcv2Stat{
    private CalcV2 calcV2;
    public StartCalcStat(CalcV2 calcV2) {
        this.calcV2 = calcV2;
    }

    @Override
    public void processNumber(String ch) {
        calcV2.setOperand1(Integer.parseInt(ch));
        calcV2.setCalcStat(new FirstOperandCalcStat(calcV2));

    }

    @Override
    public void processOperator(char ch) {

    }
}
