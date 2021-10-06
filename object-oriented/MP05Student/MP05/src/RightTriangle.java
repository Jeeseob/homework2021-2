import java.awt.*;

public class RightTriangle extends Shape{
    public RightTriangle(String type, Point[] points) {
        super(type, points);
    }

    @Override
    public double calcArea() {
        Point[] points = super.getPoints();
        double yPointMin = points[0].getY();
        double yPointMax = points[0].getY();

        double xPointMin = points[0].getX();
        double xPointMax = points[0].getX();

        for (int i = 1; i<points.length; i++){
            yPointMax = Math.max(yPointMax,points[i].getY());
            yPointMin = Math.min(yPointMin,points[i].getY());

            xPointMax = Math.max(xPointMax, points[i].getX());
            xPointMin = Math.min(xPointMin, points[i].getX());
        }
        return ((xPointMax - xPointMin) * (yPointMax - yPointMin))/2 ;
    }
}
