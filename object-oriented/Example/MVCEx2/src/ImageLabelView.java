import javax.swing.*;

public class ImageLabelView extends LabelView {
    public ImageLabelView(Model model, String title, int x, int y, int width, int height) {
        super(model, title, x, y, width, height);
    }

    public void show(Image image) {
        label.setIcon(image.getImageIcon());
        label.validate();
    }
}
