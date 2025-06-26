package entity;

import java.util.List;

public class NPCPath {

    public static class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final List<Point> points;
    private int currentPointIndex;

    public NPCPath(List<Point> points) {
        this.points = points;
        this.currentPointIndex = 0;
    }

    public List<Point> getPoints() {
        return points;
    }
    
    public Point getNextPoint() {
        currentPointIndex = (currentPointIndex + 1) % points.size();
        return points.get(currentPointIndex);
    }

}
