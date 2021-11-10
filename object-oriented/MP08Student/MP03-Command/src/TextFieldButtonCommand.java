import javax.swing.*;
import java.awt.event.ActionListener;

public class TextFieldButtonCommand extends CommandButton implements Command {
    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Add TextField Window Observer";
    private PrimeObservableThread primeThread;
    private boolean textFieldObserverAdded = true;
    private TextFieldWindow textFieldWindow;

    public TextFieldButtonCommand(String text, ActionListener listener, int width, int height,
                                  TextFieldWindow window, PrimeObservableThread thread) {
        super(text, listener, width, height);
        primeThread = thread;
        textFieldWindow = window;
    }

    public void execute() {
        if (textFieldObserverAdded) {
            primeThread.removeObserver(textFieldWindow);
            setText(ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE);
            textFieldObserverAdded = false;
        }
        else {
            primeThread.addObserver(textFieldWindow);
            setText(REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE);
            textFieldObserverAdded = true;
        }
    }
}
