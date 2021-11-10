import javax.swing.*;
import java.awt.event.WindowListener;

public abstract class FrameWindow {
    private JFrame frame;

    public FrameWindow() {
        frame = null;
    }

    public FrameWindow(String title, int x, int y, int width, int height) {
        frame = createWindow(title, x, y, width, height);
    }

//    public FrameWindow(String title, int x, int y, int width, int height, WindowListener lis) {
//        frame = createWindow(title, x, y, width, height);
//        frame.addWindowListener(lis);
//    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame createWindow(String title, int x, int y, int width, int height) {
        JFrame frame;
        frame = new JFrame(title);
        frame.setBounds(x, y, width, height);
        JPanel panel = createPanel(width, height);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public void closeWindow() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void addWindowListener(WindowListener lis) {
        frame.addWindowListener(lis);
    }

    public abstract JPanel createPanel(int width, int height);
}
