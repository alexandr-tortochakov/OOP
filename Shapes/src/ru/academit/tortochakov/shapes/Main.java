package ru.academit.tortochakov.shapes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(0, 0.1, 0, 0, 0, 0.1);
        System.out.println(triangle.getArea());

        Square square = new Square(9);
        System.out.println(square.getPerimeter());

        Square square1 = new Square(16);

        Rectangle rectangle = new Rectangle(5, 9);
        System.out.println(rectangle.getPerimeter());

        Circle circle = new Circle(13);
        System.out.println(circle.getArea());

        Shape[] shapes = {triangle, square, square1, rectangle, circle};
        printMaxArea(shapes);
        printSecondPerimeter(shapes);

        System.out.println("Check equals: " + square.equals(rectangle));
        System.out.println(square.hashCode());
        System.out.println(square1.hashCode());
    }

    public static void printMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, (o1, o2) -> (int) (o1.getArea() - o2.getArea()));
        System.out.println(shapes[shapes.length - 1]);
    }

    public static void printSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, (o1, o2) -> (int) (o1.getPerimeter() - o2.getPerimeter()));
        System.out.println(shapes[shapes.length - 2]);
    }
}