import java.awt.*;

public class RectangularShapeFactory implements ShapeFactory {
    @Override
    public Shape create(String type, Point[] points) {
        if (type.equals("Rectangle")) {
            return new Rectangle(type, points);
        }
        else if (type.equals("Parallelogram")) {
            return new Parallelogram(type, points);
        }
        else if (type.equals("Trapezoid")) {
            return new Trapezoid(type, points);
        }
        return null;
    }
}
