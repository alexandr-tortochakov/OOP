package ru.academit.tortochakov.shapes;

public class Rectangle implements Shape {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return (height + width) * 2;
    }

    @Override
    public String toString() {
        return "Rectangle: width = " + width + " height = " + height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return height == r.height && width == r.width;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int) height;
        hash = PRIME * hash + (int) width;
        return hash;
    }
}
