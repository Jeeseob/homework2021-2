import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Parallelogram extends Shape{
    public Parallelogram(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        Point[] points = super.getPoints();
        double yPointMax = points[0].getY();
        double yPointMin = points[0].getY();

        for(int i = 1; i < points.length; i++) {
            yPointMax = Math.max(yPointMax, points[i].getY());
            yPointMin = Math.min(yPointMin, points[i].getY());
        }

        ArrayList<Double> weight = new ArrayList<Double>();
        for (int i = 0; i< points.length; i++ ) {
            weight.add(points[i].getX());
        }
        Collections.sort(weight);


        return (weight.get(3) - weight.get(1))* (yPointMax-yPointMin);
    }

}
