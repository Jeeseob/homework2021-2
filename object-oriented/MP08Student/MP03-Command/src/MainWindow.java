import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends FrameWindow implements ActionListener {
    private static final String MAIN_TITLE = "Main Window";
    private static final String TEXTFIELD_WINDOW_TITLE = "TextField Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";
    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String STOP_THREAD_BUTTON_TITLE = "Stop Generating Prime Number";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;
    private static final int GAP = 50;

    private CommandButton stopButton;
    private CommandButton updateTextFieldObserverButton;
    private CommandButton updateLabelObserverButton;
    private PrimeObservableThread primeThread;
    private TextFieldWindow textFieldWindow;
    private LabelWindow labelWindow;
    private TextFieldButtonCommand textFieldButtonCommand;
    private LabelButtonCommand labelButtonCommand;
    private StopButtonCommand stopButtonCommand;

    public MainWindow(String title) {
        //super(title, X, Y, WIDTH, HEIGHT);
        super();
        textFieldWindow = new TextFieldWindow(TEXTFIELD_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT);
        labelWindow = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);
        primeThread = new PrimeObservableThread(); // 객체 생성

        JFrame frame = createWindow(title, X, Y, WIDTH, HEIGHT);
        setFrame(frame);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                primeThread.stopRunning();
                textFieldWindow.closeWindow();
                labelWindow.closeWindow();
                System.exit(0);
            }
        });

        primeThread.addObserver(textFieldWindow);
        primeThread.addObserver(labelWindow);
        primeThread.run();  // 소수 생성 시작. 이 함수가 실행된 후에는 stopButton이 눌리기 전까지 무한 반복됨
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        updateTextFieldObserverButton = new TextFieldButtonCommand(
                REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE, this, width, height,
                textFieldWindow, primeThread);
        panel.add(updateTextFieldObserverButton);
        updateLabelObserverButton = new LabelButtonCommand(REMOVE_LABEL_OBSERVER_BUTTON_TITLE, this, width, height,
                                                 labelWindow, primeThread);
        panel.add(updateLabelObserverButton);
        stopButton = new StopButtonCommand(STOP_THREAD_BUTTON_TITLE, this, width, height, primeThread);
        panel.add(stopButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Command) {
            ((Command) e.getSource()).execute();
        }
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
