package ru.academit.tortochakov.shapes;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(0, 1, 0, 0, 0, 1);
        System.out.println(triangle.getArea());

        Square square = new Square(9);
        System.out.println(square.getPerimeter());

        Rectangle rectangle = new Rectangle(5, 9);
        System.out.println(rectangle.getPerimeter());

        Circle circle = new Circle(15);
        System.out.println(circle.getArea());

        Shapes[] shapes = {triangle, square, rectangle, circle};
        printMaxArea(shapes);
        printSecondPerimeter(shapes);

        System.out.println(square.equals(rectangle));
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
        System.out.println(shapes[x].toString());
    }

    public static void printSecondPerimeter(Shapes[] shapes) {
        double max = 0;
        double second = 0;
        int index = 0;
        if (shapes == null) {
            System.out.println("В массиве нет элементов");
        } else if (shapes.length <= 1) {
            System.out.println("В массиве меньше двух элементов");
        } else if (shapes.length > 1) {
            for (int i = 0; i < shapes.length; i++) {
                double perimeter = shapes[i].getPerimeter();
                if (max < perimeter) {
                    second = max;
                    max = perimeter;
                } else if (perimeter > second) {
                    second = perimeter;
                    index = i;
                }
            }
        }
        System.out.println(shapes[index].toString());
    }
}