package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Shape {
    private ArrayList<Point> pointsSeries = new ArrayList<>();
    private Point center;
    private double radius;

    public Shape(String str) {
        double xSum = 0, ySum = 0;
        int cnt = 0;

        Scanner sc = new Scanner(str);
        while (sc.hasNext()) {
            ++cnt;
            double x = Double.valueOf(sc.next());
            double y = Double.valueOf(sc.next());
            xSum += x;
            ySum += y;
            Point point = new Point(x, y);
            pointsSeries.add(point);
        }

        center = new Point(xSum / cnt, ySum / cnt);
        radius = calculateRadius();
    }

    private double calculateRadius() {
        double res = Double.MAX_VALUE;
        for (Point point : pointsSeries) {
            res = Double.min(res, center.distance(point));
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("point: ");
        for (Point p : pointsSeries) {
            sb.append(p.toString());
        }
        sb.append(", center: " + center.toString() + ", radius = " + radius);

        return sb.toString();
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public ArrayList<Point> getPointsSeries() {
        return pointsSeries;
    }

    public boolean crossess(Shape s) {
        int len = pointsSeries.size();
        boolean previous = false;
        for (int i = 0; i < len - 1; ++i) {
            boolean startPoint = (i == 0 ? isInCircle(s, pointsSeries.get(i)) : previous);
            boolean endPoint = isInCircle(s, pointsSeries.get(i + 1));
            if (startPoint ^ endPoint) {
                return true;
            }
            previous = endPoint;
        }
        return false;
    }

    private boolean isInCircle(Shape s, Point p) {
        return p.distance(s.getCenter()) < s.getRadius();
    }

    public int encircles(Shape s) {
        double dis = center.distance(s.getCenter());
        if (s.getRadius() < radius && isInCircle(this, s.getCenter()) && dis < radius - s.getRadius()) {
            return 2;
        }
        else if (dis > Math.abs(radius - s.getRadius()) && dis < radius + s.getRadius()) {
            return 1;
        }

        return 0;
    }
}
