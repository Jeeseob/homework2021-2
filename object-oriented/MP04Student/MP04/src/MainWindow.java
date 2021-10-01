import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends FrameWindow {
    private static final String MAIN_TITLE = "Main Window";
    private static final String TEXTFIELD_WINDOW_TITLE = "TextField Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final String TEXTFIELD_OBSERVER_BUTTON_TITLE = "Update TextField Window Observer";
    private static final String LABEL_OBSERVER_BUTTON_TITLE = "Update Label Window Observer";
    private static final String STOP_THREAD_BUTTON_TITLE = "Stop Generating Prime Number";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 100;
    private static final int GAP = 50;

    private ArrayList<String> displayList;
    private JFrame frame;

    public JFrame createWindow(String title, int x, int y, int width, int height) {
        JFrame frame;
        // 전체 화면의 뼈대를 구성함 (윈도우 틀을 만들어줌)
        frame = new JFrame(title);
        // 화면 크기 지정 (윈도우 화면의 너비와 높이는 매개 변수로 전달됨)
        frame.setBounds(x, y, width, height);

        // createPanel() 함수를 호출해서 파일에서 읽은 디스플레이 구성 요소에 따라 디스플레이를 조합하고, 전체 디스플레이를 구성하는 패널을 돌려 받음
        // 실질적인 화면 구성은 createPanel()함수에서 일어남
        JPanel panel = createPanel(width, height);

        // 윈도우에 전체 패널을 붙임        
        frame.getContentPane().add(panel);
        frame.pack();

        // 윈도우를 화면에 보이게 만듦
        frame.setVisible(true);
        return frame;
    }

    public void closeWindow() {
        frame.setVisible(false);
        frame.dispose();
    }

    public MainWindow(String title, ArrayList<String> list) {
        displayList = list;
        // 메인 윈도우의 크기는 주어진 너비와 높이 * (디스플레이 구성 요소 + 1)      
        // 실제 화면 구성은 createWindow() 함수에서 처리함
        frame = createWindow(title, X, Y, WIDTH, HEIGHT * (displayList.size() + 1));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
                System.exit(0);
            }
        });
    }

    public JPanel createPanel(int width, int height) {
        // 제일 바탕에 놓일 패널 생성
        JPanel panel = new JPanel();
        // 패널의 레이아웃은 BoxLayout.Y_AXIS 형태로 지정. 패널에 위젯 또는 다른 패널을 추가하면, 세로축 방향으로 추가(add)한 순서대로 나열됨
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // 패널의 전체 크기 지정
        panel.setMinimumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));

        // 기본 컴포넌트 생성. HudDisplay는 가장 기본적인 디스플레이 구성요소가 됨
        Display display = new HudDisplay(WIDTH, HEIGHT);

        // 리스트에 있는 내용을 바탕으로 장식 추가하기. 장식을 추가하는 것은 현재까지 구성된 디스플레이 패널을 감쌀 수 있는 새로운 패널을 생성하고,
        // 그 안에 여태까지 만들어진 패널과 글씨를 쓸 수 있는 LabelPanel을 추가함
        // 새로운 장식이 추가될 때마다 새로운 패널이 생성되고, 그 안에 기존 패널이 추가됨(add)
        for (String name : displayList) {
            if (name.equals("speed")) {
                // display 변수가 참조하는 기존 디플레이에 속도계 디스플레이를 덧 붙임
                display = new SpeedometerDisplay(display, WIDTH, HEIGHT);
            }
            else if (name.equals("time")) {
                // display 변수가 참조하는 기존 디플레이에 시계 디스플레이를 덧 붙임
                display = new TimeDisplay(display, WIDTH, HEIGHT);
            }
            else if (name.equals("weather")) {
                // display 변수가 참조하는 기존 디플레이에 날씨 디스플레이를 덧 붙임
                display = new WeatherDisplay(display, WIDTH, HEIGHT);
            }
        }
        // 장식이 모두 끝나면 최종 디스플레이 패널을 생성하도록 함. 장식 패널들을 포함하는 최종 패널이 만들어지고, 이것을 새로운 패널에 부착 시킴
        panel.add(display.create());

        // 높이를 출력
        System.out.println("disply.height = " + display.getHeight());

        // 디스플레이마다 각각의 내용을 화면에 보임
        display.show();
        return panel;
    }

    public static void main(String[] args) {
        final String displayFileName = "displays.txt";
        ArrayList<String> list;
        LoadHudDisplays loadDisplay = new LoadHudDisplays(displayFileName);
        list = loadDisplay.load();
        System.out.printf("display.size() = %d\n", list.size());
        for (String s : list) {
            System.out.println(s);
        }

        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE, list);
    }
}
