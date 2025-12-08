import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Polyline {
    private List<Point> points;

    public Polyline() {
        this.points = new ArrayList<>();
    }

    public Polyline(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public String toString() {
        return "Линия " + points.stream()
                .map(Point::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}