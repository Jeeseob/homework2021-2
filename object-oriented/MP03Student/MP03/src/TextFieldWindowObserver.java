public class TextFieldWindowObserver implements Observer {
    private PrimeData primeData = new PrimeData();
    private TextFieldWindow textFieldWindow;

    public TextFieldWindowObserver(TextFieldWindow textFieldWindow) {
        this.textFieldWindow = textFieldWindow;
    }
    @Override
    public void update(Subject subject, Object object) {
        if(object instanceof PrimeData) {
            PrimeData primeData = (PrimeData)object;
            this.primeData.setPrimeNumber(primeData.getPrimeNumber());
            textFieldWindow.updateText(Integer.toString(primeData.getPrimeNumber()));
        }
    }
}
