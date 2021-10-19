import java.awt.*;

public abstract class Shape {
    String type;
    protected Point[] points;

    public Shape(String type, Point[] points) {
        this.type = type;
        this.points = points;
    }

    public abstract double calcArea();
    public String toString() {
        String s = type + "\n";
        for (int i = 0; i < points.length; i++) {
            s += ("P" + i + ": " + points[i].toString() + "\n");
        }
        s += ("area: " + calcArea());
        return s;
    }
}
