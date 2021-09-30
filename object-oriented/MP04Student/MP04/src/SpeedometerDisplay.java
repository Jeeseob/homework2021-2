import javax.swing.*;
import java.awt.*;

public class SpeedometerDisplay extends DisplayDecorator {

    private Display displayComponent;

    public SpeedometerDisplay(Display display, int width, int height) {
        super(display, width, height);
    }

    @Override
    public JPanel create() {

        return null;
    }

    @Override
    public void show() {
        // 패널에 보일 문자열
        //labelPanel.updateText("speed: ");
    }
}
