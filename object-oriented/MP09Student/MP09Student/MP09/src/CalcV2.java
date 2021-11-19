import java.util.Scanner;

public class CalcV2 {
    Scanner scanner;
    String inputStr;
    int operand1; // 첫 번째 피 연산자값 저장
    int operand2; // 두 번째 피 연산자값 저장
    char operator; // 사칙 연산자 저장

    Calcv2Stat calcStat;

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    CalcV2() {
        scanner = new Scanner(System.in);  // Create a Scanner object
        calcStat = new StartCalcStat(this);
    }

    public void setCalcStat(Calcv2Stat calcStat) {
        this.calcStat = calcStat;
    }

    private String getInput(String s) {
        System.out.println(s);
        return scanner.next();
    }

    void printOutResult() {
        switch (operator) {
            case '+':
                System.out.printf("%d + %d = %d\n", operand1, operand2, operand1 + operand2);
                break;

            case '-':
                System.out.printf("%d - %d = %d\n", operand1, operand2, operand1 - operand2);
                break;

            case '*':
                System.out.printf("%d * %d = %d\n", operand1, operand2, operand1 * operand2);
                break;

            case '/':
                System.out.printf("%d / %d = %d\n", operand1, operand2, operand1 / operand2);
                break;
        }
    }

    public boolean run() {
        inputStr = getInput("정수 또는 +,-,*,/,= 기호 중 한 개를 입력하세요");
        char ch = inputStr.charAt(0);
        if (ch == 'q' || ch == 'Q') { // q를 입력하면 프로그램 종료
            return false;
        }
        else if (ch >= '0' && ch <= '9') { // 정수가 입력되면
            calcStat.processNumber(inputStr);
        }
        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=') { // 연산자 처리
            calcStat.processOperator(ch);
        }
        return true;
    }
}
