package ru.academit.tortochakov.shapes;

public class Square extends Rectangle implements Shapes {
    public Square(double side) {
        super(side, side);
    }

    @Override
    public String toString() {
        return "Square: side = " + getWidth();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Square;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + (int)getWidth();
        return hash;
    }
}
