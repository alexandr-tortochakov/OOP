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

    public String toString() {
        return "Rectangle";
    }
    public boolean equals (Object o) {
        return o instanceof Rectangle;
    }
}
