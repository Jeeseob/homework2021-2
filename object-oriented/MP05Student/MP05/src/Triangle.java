import java.awt.*;
import java.util.ArrayList;

public class Triangle extends Shape {

    public Triangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        Point[] points = super.getPoints();
        ArrayList<Double> lenght = new ArrayList<Double>();

        lenght.add(distance(points[0],points[1]));
        lenght.add(distance(points[1],points[2]));
        lenght.add(distance(points[2],points[0]));

        double sumLength=0;
        for (int i = 0; i < lenght.size(); i++ ){
            sumLength += lenght.get(i) / 2;
        }

        return Math.sqrt(sumLength*(sumLength - lenght.get(0))*(sumLength - lenght.get(1))*(sumLength - lenght.get(2)));
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(),2) + Math.pow(p2.getY()-p1.getY(),2));

    }
}
