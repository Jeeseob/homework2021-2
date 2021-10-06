import java.awt.*;
import java.util.ArrayList;

public interface ShapeFactory {
    public Shape create(String type, Point[] points);
}
