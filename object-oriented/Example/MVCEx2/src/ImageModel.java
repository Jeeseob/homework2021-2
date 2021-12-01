import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Model {
    private String[] imageFileNames = { "sheep.jpg", "cat.jpg", "dog.jpg", "hedgehog.jpg" };
    private List<Image> imageList;
    private DefaultListModel listModel;
    private List<Observer> observers;
    private int selectedIndex = -1;

    public ImageModel() {
        observers = new ArrayList<Observer>();
        listModel = new DefaultListModel();
        imageList = new ArrayList<Image>();
        for (int i = 0; i < imageFileNames.length; i++) {
            addImage(imageFileNames[i]);
        }
    }

    public DefaultListModel getDefaultListModel() { return listModel; }

    public Image createImage(String fileName) {
        Image image = null;
        ImageIcon imageIcon = new ImageIcon(fileName);
        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            image = new Image(fileName, imageIcon, img.getWidth(), img.getHeight());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void addImage(String fileName) {
        Image image = createImage(fileName);
        if (image != null) {
            imageList.add(image);
            listModel.addElement(fileName);
        }
    }

    public void selectImage(int idx) {
        selectedIndex = (idx >= 0 && idx < imageList.size()) ? idx : -1;
        notifyObservers();
    }

    public Image getSelectedImage() {
        return (selectedIndex != -1) ? imageList.get(selectedIndex) : null;
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
