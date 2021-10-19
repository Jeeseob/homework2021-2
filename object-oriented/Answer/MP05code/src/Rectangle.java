import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        return Math.abs(points[1].x - points[0].x)
                * Math.abs(points[1].y - points[0].y);
    }
}
