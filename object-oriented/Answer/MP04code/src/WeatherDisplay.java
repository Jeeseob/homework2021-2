import javax.swing.*;
import java.awt.*;

public class WeatherDisplay extends InfoDisplayDecorator {
    public WeatherDisplay(Display display, int width, int height) {
        super(display, width, height);
    }

    @Override
    public String getDisplayText() {
        return "Weather: 온도: 20도, 습도: 60";
    }
}
