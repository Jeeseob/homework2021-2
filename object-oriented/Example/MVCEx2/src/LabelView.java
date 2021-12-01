import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LabelView extends FrameWindow implements Observer {
    JLabel label;
    Model model;

    public LabelView(Model model, String title, int x, int y, int width, int height) {
        super();
        this.model = model;
        model.addObserver(this);
        createWindow(title, x, y, width, height);
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        label = new JLabel();
        panel.add(label);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    public void update() {
        Image image = model.getSelectedImage();
        if (image != null) {
            show(image);
        }
    }

    public abstract void show(Image image);
}
