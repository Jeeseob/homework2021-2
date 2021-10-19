import java.awt.*;

public class Trapezoid extends Shape {
    public Trapezoid(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        double w1, w2;
        w1 = Math.abs(points[3].x - points[0].x);
        w2 = Math.abs(points[1].x - points[2].x);
        double height = Math.abs(points[1].y - points[3].y);
        return (w1 + w2) * height / 2;
    }
}
