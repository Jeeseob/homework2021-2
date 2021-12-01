import javax.swing.*;

public class Image {
    private int width;
    private int height;
    private String name;
    ImageIcon icon;

    public Image(String name, ImageIcon icon, int w, int h) {
        this.name = name;
        this.icon = icon;
        width = w;
        height = h;
    }

    public ImageIcon getImageIcon() { return icon; }

    @Override
    public String toString() {
        return "<HTML>Name: " + name + "<br>" + "Width: " + width + "<br>"
                + "Height: " + height + "<br></HTML>";
    }
}
