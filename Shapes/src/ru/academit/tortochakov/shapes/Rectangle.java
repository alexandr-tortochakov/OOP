package ru.academit.tortochakov.shapes;

import java.util.Objects;

public class Rectangle implements Shapes {
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
        return o instanceof Rectangle;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int) getWidth();
        hash = PRIME * hash + (int) getHeight();
        return hash;
    }
}
