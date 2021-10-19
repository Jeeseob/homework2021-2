import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TimeDisplay extends InfoDisplayDecorator {
    public TimeDisplay(Display display, int width, int height) {
        super(display, width, height);
    }

    @Override
    public String getDisplayText() {
        return LocalDateTime.now().toString();
    }
}
