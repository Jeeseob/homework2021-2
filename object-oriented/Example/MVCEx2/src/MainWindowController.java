import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowController extends FrameWindow implements ActionListener, ListSelectionListener {
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;
    private static final int BUTTON_HEIGHT = 50;
    private JList<String> list;
    private JButton addImageFileButton;
    private JFrame frame;
    private LabelView imageView;
    private LabelView textView;
    private Model model;

    public MainWindowController(String title) {
        super();
//        this.model = model;
        model = new ImageModel();
        frame = createWindow(title, X, Y, WIDTH, HEIGHT);

        textView = new TextLabelView(model, "Image Info", X + WIDTH + 30, Y, WIDTH, HEIGHT);
        imageView = new ImageLabelView(model, "Image", X, Y + HEIGHT + 50, WIDTH * 2 + 30, HEIGHT * 2);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public JFrame getFrameWindow() { return frame; }

    public void addImage(String name) {
        model.addImage(name);
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));

        addImageFileButton = new AddImageCommandButton("Add Image File...", this);
        addImageFileButton.addActionListener(this);
        buttonPanel.add(addImageFileButton);
        panel.add(buttonPanel);

        list = new JList<String>(model.getDefaultListModel());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한 번에 한 개만 선택 가능
        list.addListSelectionListener(this);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(width, height - BUTTON_HEIGHT));
        panel.add(listScroller, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Command) {
            Command command = (Command) e.getSource();
            command.execute();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = list.getSelectedIndex();
        if (idx != -1) { // 선택되지 않은 경우에 -1 반환
            model.selectImage(idx);
        }
    }
}
