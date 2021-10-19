import java.awt.*;

public class RightTriangle extends Shape {
    public RightTriangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        return Math.abs(points[2].x - points[1].x)
                * Math.abs(points[2].y - points[0].y) / 2;
    }
}
