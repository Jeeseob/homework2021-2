import javax.swing.*;
import java.awt.event.ActionListener;

public class StopButtonCommand extends CommandButton implements Command {
    private PrimeObservableThread primeThread;

    public StopButtonCommand(String text, ActionListener listener, int width, int height, PrimeObservableThread thread) {
        super(text, listener, width, height); // JButton();
        primeThread = thread;
    }

    public void execute() {
        primeThread.stopRunning();
    }
}
