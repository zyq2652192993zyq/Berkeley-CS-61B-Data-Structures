package main;

public class Point {
    private double x, y;

    public Point() {
        x = y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public  Point(Point p) {
        x = p.getX();
        y = p.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow((x - p.getX()), 2) + Math.pow((y - p.getY()), 2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();;
        sb.append("(" + x + ", " + y + ")");
        return sb.toString();
    }
}
