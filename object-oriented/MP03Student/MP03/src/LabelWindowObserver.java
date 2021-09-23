public class LabelWindowObserver implements Observer{
    private PrimeData primeData = new PrimeData();
    private LabelWindow labelWindow;

    public LabelWindowObserver(LabelWindow labelWindow) {
        this.labelWindow = labelWindow;
    }
    @Override
    public void update(Subject subject, Object object) {
        if(object instanceof PrimeData) {
            PrimeData primeData = (PrimeData)object;
            this.primeData.setPrimeNumber(primeData.getPrimeNumber());
            labelWindow.updateText(Integer.toString(primeData.getPrimeNumber()));
        }
    }
}