import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CalcGUIV1 extends JFrame implements ActionListener {
    final static int WINDOW_WIDTH = 400;
    final static int WINDOW_HEIGHT = 300;
    final static int COMPONENT_HEIGHT = 40;
    final static int BUTTON_WIDTH = 50;

    String[] buttonText = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=" };
    JButton[] buttons = new JButton[buttonText.length];
    Calculator calculator;

    Dimension displayDimension = new Dimension(WINDOW_WIDTH - 20, COMPONENT_HEIGHT);
    Dimension buttonDimension = new Dimension(BUTTON_WIDTH, COMPONENT_HEIGHT);

    JLabel display = new JLabel(); // 숫자 값 및 결과 출력 화면
    NumberButtonCommand numberButtonCommand;
    EqualButtonCommand equalButtonCommand;
    OperatorButtonCommand operatorButtonCommand;
    NoCommand noCommand = new NoCommand();

    Invoker invoker = new Invoker();

    CalcGUIV1() {
        super("CalcGUIV1");
        calculator = new Calculator();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Font labelFont = display.getFont();
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font(labelFont.getName(), Font.PLAIN, COMPONENT_HEIGHT - 5));
        display.setPreferredSize(new Dimension(displayDimension));
        setResizable(false);
        setLayout(new BorderLayout());
        add(getDisplayPanel(), BorderLayout.NORTH);
        add(getButtonPanel(), BorderLayout.CENTER);
        clear();
    }

    public JPanel getDisplayPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        displayPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        displayPanel.setPreferredSize(displayDimension);
        displayPanel.add(display);
        return displayPanel;
    }
    public JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,3,10,5));
        for (int i = 0; i < buttonText.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(buttonText[i]);
            buttons[i].setPreferredSize(buttonDimension);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        return buttonPanel;
    }

    public void clear() {
        display.setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton cmdButton = (JButton) e.getSource();
        numberButtonCommand = new NumberButtonCommand(calculator,cmdButton, display);
        equalButtonCommand = new EqualButtonCommand(calculator, display);
        operatorButtonCommand = new OperatorButtonCommand(calculator, cmdButton);
        invoker.setCommand(noCommand);

        if (cmdButton == buttons[0] || cmdButton == buttons[1]  || cmdButton == buttons[2]  ||
                cmdButton == buttons[3]  || cmdButton == buttons[4]  || cmdButton == buttons[5]  ||
                cmdButton == buttons[6]  || cmdButton == buttons[7]  || cmdButton == buttons[8]  ||
                cmdButton == buttons[9] ) { // 0-9 버튼
            invoker.setCommand(numberButtonCommand);
//            if (calculator.isOperand1Set() && calculator.isOperatorSet()) { // 첫 번째 피연산자와 연산자가 지정되었다면 두 번째 피연산자 값으로 사용
//                int num2 = Integer.parseInt(cmdButton.getText());
//                calculator.setOperand2(num2);
//                display.setText("" + num2);
//                calculator.setOperand2Set(true);
//            }
//            else {  // 첫 번째 피연산자 값 지정
//                int num1 = Integer.parseInt(cmdButton.getText());
//                display.setText("" + num1);
//                calculator.setOperand1(num1);
//                calculator.setOperand1Set(true);
//            }
        }
        else if (cmdButton == buttons[14]) { // = 버튼
            invoker.setCommand(equalButtonCommand);
//            int result = 0;
//            if (calculator.isOperand1Set() && calculator.isOperand2Set() && calculator.isOperatorSet()) { // 두 개 피 연산자값과 연산자가 지정되었다면
//                if (calculator.getOperator() == '+') {
//                    result = calculator.getOperand1() + calculator.getOperand2();
//                }
//                else if (calculator.getOperator() == '-') {
//                    result = calculator.getOperand1() - calculator.getOperand2();
//                }
//                else if (calculator.getOperator() == '*') {
//                    result = calculator.getOperand1() * calculator.getOperand2();
//                }
//                else if (calculator.getOperator() == '/') {
//                    result = calculator.getOperand1() / calculator.getOperand2();
//                }
//            }
//            display.setText("" + result);
//            calculator.clearFlags();
        }
        else if (cmdButton == buttons[10] || cmdButton == buttons[11] ||
                cmdButton == buttons[12] || cmdButton == buttons[13]) { // +, -, *, / 버튼
            invoker.setCommand(operatorButtonCommand);
//            if (calculator.isOperand1Set()) {  // 첫 번째 피연산자 값이 지정되어야만 연산자 처리 가능
//                calculator.setOperatorSet(true);
//                calculator.setOperator(cmdButton.getText().charAt(0));
//            }
        }
        invoker.pressButton();
    }

    public static void main(String[] args) {
        CalcGUIV1 c = new CalcGUIV1();
        c.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setVisible(true);
    }
}

