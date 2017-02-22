package ru.academit.tortochakov.shapes;

public class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public double getWidth() {
        return side;
    }

    public double getHeight() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Square: side = " + side;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Square)) {
            return false;
        }
        Square s = (Square) o;
        return (o == this) || (side == s.side);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = PRIME * hash + side;
        return hash;
    }
}
