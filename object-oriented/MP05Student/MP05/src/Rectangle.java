import java.awt.*;
import java.util.Arrays;

public class Rectangle extends Shape{

    public Rectangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        Point[] points = super.getPoints();

        double xPointMin = Math.min(points[0].getX(), points[1].getX());
        double xPointMax = Math.max(points[0].getX(), points[1].getX());

        double yPointMin = Math.min(points[0].getY(), points[1].getY());
        double yPointMax = Math.max(points[0].getY(), points[1].getY());

        return (xPointMax - xPointMin) * (yPointMax - yPointMin);

    }

}
