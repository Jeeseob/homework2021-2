import javax.swing.*;

public class WeatherDisplay extends DisplayDecorator {
    WeatherDisplay(Display display, int width, int height) {
        super(display, width, height);
    }

    @Override
    public JPanel create() {
        return null;
    }

    @Override
    public void show() {

    }
}
