import java.awt.*;

public class RectangularShapeFactory implements ShapeFactory{

    @Override
    public Shape create(String type, Point[] points) {
        Shape shape = null;
        if (type.equals("Rectangle")) {
            shape = new Rectangle(type, points);
        }
        else if (type.equals("Parallelogram")) {
            shape = new Parallelogram(type, points);
        }
        else if (type.equals("Trapezoid")) {
            shape = new Trapezoid(type, points);
        }
        return shape;
    }
}
