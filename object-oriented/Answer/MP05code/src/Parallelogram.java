import java.awt.*;

public class Parallelogram extends Shape {
    public Parallelogram(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        return Math.abs(points[3].x - points[0].x)
                * Math.abs(points[1].y - points[3].y);
    }
}
