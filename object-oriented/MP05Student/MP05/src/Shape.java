import java.awt.*;

public abstract class Shape{
    protected Point[] points;
    private String type;

    public Shape(String type, Point[] points) {
        this.points = points;
        this.type = type;
    }

    public Point[] getPoints(){
        return points;
    }

    @Override
    public String toString() {
        return type + "\n" + answerPoint(points) + "area: " + calcArea()+"\n";
    }

    private String answerPoint(Point[] points) {
        String answer = "";
        for (int i = 0; i < points.length; i++){
            answer = answer +"P"+i+": java.awt.Point[x="+points[i].getX()+", y="+points[i].getY()+"]\n";
        }
        return answer;
    }
    abstract public double calcArea();
}
