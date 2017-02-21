package ru.academit.tortochakov.shapes;

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

        Shapes[] shapes = {triangle, square, square1, rectangle, circle};
        printMaxArea(shapes);
        printSecondPerimeter(shapes);

        System.out.println(square.equals(rectangle));
        System.out.println(square.hashCode());
        System.out.println(square1.hashCode());
    }

    public static void printMaxArea(Shapes[] shapes) {
        double max = 0;
        int x = 0;
        for (int i = 0; i < shapes.length; i++) {
            double a = shapes[i].getArea();
            if (max < a) {
                max = a;
                x = i;
            }
        }
        System.out.println(shapes[x]);
    }

    public static void printSecondPerimeter(Shapes[] shapes) {
        double max = 0;
        double second = 0;
        int secondIndex = 0;
        int maxIndex = 0;
        if (shapes == null) {
            System.out.println("В массиве нет элементов");
            return;
        } else if (shapes.length <= 1) {
            System.out.println("В массиве меньше двух элементов");
            return;
        } else if (shapes.length > 1) {
            for (int i = 0; i < shapes.length; i++) {
                double perimeter = shapes[i].getPerimeter();
                if (max < perimeter) {
                    second = max;
                    secondIndex = maxIndex;
                    max = perimeter;
                    maxIndex = i;
                } else if (perimeter > second) {
                    second = perimeter;
                    secondIndex = i;
                }
            }
        }
        System.out.println(shapes[secondIndex]);
    }
}