package ru.academit.tortochakov.shapes;

public class Circle implements Shapes {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    public String toString() {
        return "Circle: radius " + radius;
    }

    public boolean equals(Object o) {
        return o instanceof Circle;
    }

    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int) getWidth();
        return hash;
    }
}
