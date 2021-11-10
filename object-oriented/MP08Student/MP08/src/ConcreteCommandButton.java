import javax.swing.*;

public class ConcreteCommandButton implements Command{

    private JButton[] buttons;
    private Calculator calculator;

    private JButton cmdButton;
    private int num;

    public ConcreteCommandButton(JButton[] buttons) {
        this.buttons = buttons;
        
        calculator = new Calculator();

    }

    @Override
    public String execute() {
        if (cmdButton == buttons[0] || cmdButton == buttons[1] || cmdButton == buttons[2] ||
                cmdButton == buttons[3] || cmdButton == buttons[4] || cmdButton == buttons[5] ||
                cmdButton == buttons[6] || cmdButton == buttons[7] || cmdButton == buttons[8] ||
                cmdButton == buttons[9]) {
            if (calculator.isOperand1Set() && calculator.isOperatorSet()) { // 첫 번째 피연산자와 연산자가 지정되었다면 두 번째 피연산자 값으로 사용
                num = Integer.parseInt(cmdButton.getText());
                calculator.setOperand2(num);
                calculator.setOperand2Set(true);
                return String.valueOf(num);
            } else {  // 첫 번째 피연산자 값 지정
                num = Integer.parseInt(cmdButton.getText());
                calculator.setOperand1(num);
                calculator.setOperand1Set(true);
                return String.valueOf(num);
            }
        }
        else if (cmdButton == buttons[14]) { // = 버튼
            num = 0;
            if (calculator.isOperand1Set() && calculator.isOperand2Set() && calculator.isOperatorSet()) { // 두 개 피 연산자값과 연산자가 지정되었다면
                if (calculator.getOperator() == '+') {
                    num = calculator.getOperand1() + calculator.getOperand2();
                } else if (calculator.getOperator() == '-') {
                    num = calculator.getOperand1() - calculator.getOperand2();
                } else if (calculator.getOperator() == '*') {
                    num = calculator.getOperand1() * calculator.getOperand2();
                } else if (calculator.getOperator() == '/') {
                    num = calculator.getOperand1() / calculator.getOperand2();
                }
                else {
                    //Error
                    return null;
                }
            }
            calculator.clearFlags();
            return String.valueOf(num);
        }
        else if (cmdButton == buttons[10] || cmdButton == buttons[11] ||
                cmdButton == buttons[12] || cmdButton == buttons[13]) { // +, -, *, / 버튼
            if (calculator.isOperand1Set()) {  // 첫 번째 피연산자 값이 지정되어야만 연산자 처리 가능
                calculator.setOperatorSet(true);
                calculator.setOperator(cmdButton.getText().charAt(0));
            }
            return String.valueOf(cmdButton.getText().charAt(0));
        }
        //Error
        return null;
    }
}
