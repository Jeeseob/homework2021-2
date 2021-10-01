import javax.swing.*;

public abstract class DisplayDecorator extends Display {
    protected Display display;

    DisplayDecorator(Display display, int width, int height) {
        super(width, height);
        this.display = display;
    }

    public JPanel create() {
        return this.display.create();
    }

    public void show() {
        this.display.show();
    }
}

