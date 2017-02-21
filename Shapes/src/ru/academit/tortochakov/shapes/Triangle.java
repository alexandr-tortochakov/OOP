package ru.academit.tortochakov.shapes;

public class Triangle implements Shapes {
    private double x1, x2, x3, y1, y2, y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }


    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    private double getSide(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public double getPerimeter() {
        double a = getSide(x1, x2, y1, y2);
        double b = getSide(x2, x3, y2, y3);
        double c = getSide(x1, x3, y1, y3);
        return a + b + c;
    }

    public double getArea() {
        double a = getSide(x1, x2, y1, y2);
        double b = getSide(x2, x3, y2, y3);
        double c = getSide(x1, x3, y1, y3);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return "Triangle";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Triangle;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int) x1;
        hash = PRIME * hash + (int) x2;
        hash = PRIME * hash + (int) x3;
        hash = PRIME * hash + (int) y1;
        hash = PRIME * hash + (int) y2;
        hash = PRIME * hash + (int) y3;
        return hash;
    }
}
