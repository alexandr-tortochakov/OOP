package ru.academit.tortochakov.vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {3, 4, 6, 7};
        Vector v1 = new Vector(array);
        double[] arr2 = {3, 4, 6, 7.};
        Vector v2 = new Vector(arr2);

        System.out.print("v1 = v1 + v2: ");
        v1.add(v2);
        System.out.println(v1);

        System.out.print("v1 = v1 - v2: ");
        v1.subtract(v2);
        System.out.println(v1);

        System.out.print("v1 = -v1: ");
        v1.turn();
        System.out.println(v1);

        System.out.print("v3 = v2 + v1: ");
        System.out.println(Vector.addition(v2, v1));

        System.out.print("v1 * v2: ");
        System.out.println(Vector.dotProduct(v1, v2));
        System.out.println(v1.equals(v2));
    }
}
