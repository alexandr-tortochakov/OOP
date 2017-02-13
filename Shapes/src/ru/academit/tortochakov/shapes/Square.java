package ru.academit.tortochakov.shapes;

public class Square extends Rectangle implements Shapes {
    public Square(double side) {
        super(side, side);
    }
    public String toString() {
        return "Square";
    }

    public boolean equals (Object o) {
        return o instanceof Square;
    }
}
