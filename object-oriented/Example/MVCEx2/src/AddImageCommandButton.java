import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddImageCommandButton extends JButton implements Command {
    ImageIcon icon;
    MainWindowController controller;
    String currentDirectory = "";

    AddImageCommandButton(String text, MainWindowController controller) {
        super(text);
        this.controller = controller;
    }

    public void setProperties(ActionListener listener, int width, int height) {
        addActionListener(listener);
        Dimension buttonDimension = new Dimension(width, height);
        setMaximumSize(buttonDimension);
        setMinimumSize(buttonDimension);
        setPreferredSize(buttonDimension);
    }

    public void setImageIcon(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public void execute() {
        JFileChooser c = new JFileChooser(currentDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Files", "jpg", "image");
        c.setFileFilter(filter);
        int val = c.showOpenDialog(null);//window.getFrameWindow());
        if (val == JFileChooser.APPROVE_OPTION) {
            String fileName = c.getSelectedFile().getName();
            currentDirectory = c.getCurrentDirectory().toString();
            controller.addImage(currentDirectory + "\\" + fileName);
        }
    }
}
