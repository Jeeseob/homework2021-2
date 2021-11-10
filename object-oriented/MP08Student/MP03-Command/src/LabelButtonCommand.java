import javax.swing.*;
import java.awt.event.ActionListener;

public class LabelButtonCommand extends CommandButton implements Command {
    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String ADD_LABEL_OBSERVER_BUTTON_TITLE = "Add TextField Window Observer";

    private PrimeObservableThread primeThread;
    private LabelWindow labelWindow;
    private boolean labelObserverAdded = true;

    public LabelButtonCommand(String text, ActionListener listener, int width, int height,
                              LabelWindow window, PrimeObservableThread thread) {
        super(text, listener, width, height);
        primeThread = thread;
        labelWindow = window;
    }

    public void execute() {
        if (labelObserverAdded) {
            primeThread.removeObserver(labelWindow);
            setText(ADD_LABEL_OBSERVER_BUTTON_TITLE);
            labelObserverAdded = false;
        }
        else {
            primeThread.addObserver(labelWindow);
            setText(REMOVE_LABEL_OBSERVER_BUTTON_TITLE);
            labelObserverAdded = true;
        }
    }
}
