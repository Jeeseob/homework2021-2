import java.awt.*;

public class Triangle extends Shape {
    public Triangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        double a, b, c, s;
        a = distance(points[0], points[1]);
        b = distance(points[1], points[2]);
        c = distance(points[0], points[2]);
        s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x)
                         + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
