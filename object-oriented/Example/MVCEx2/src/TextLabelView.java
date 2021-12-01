public class TextLabelView extends LabelView {
    public TextLabelView(Model model, String title, int x, int y, int width, int height) {
        super(model, title, x, y, width, height);
    }

    public void show(Image image) {
        label.setText(image.toString());
        label.validate();
    }
}
