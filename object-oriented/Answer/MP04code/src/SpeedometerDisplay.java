class SpeedometerDisplay extends InfoDisplayDecorator {
    public SpeedometerDisplay(Display display, int width, int height) {
        super(display, width, height);
    }

    @Override
    public String getDisplayText() {
        return "Speed: 50";
    }
}