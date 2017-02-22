package ru.academit.tortochakov.shapes;

public class Circle implements Shape {
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

    @Override
    public String toString() {
        return "Circle: radius " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) {
            return false;
        }
        Circle c = (Circle) o;
        return (o == this) || (radius == c.radius);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int) radius;
        return hash;
    }
}
