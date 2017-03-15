package ru.academit.tortochakov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(double[] array) {
        this.vector = array.clone();
    }

    public Vector(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше 0");
        }
        this.vector = new double[capacity];
    }

    public Vector(Vector v) {
        vector = v.vector.clone();
    }

    public Vector(int capacity, double[] array) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше 0");
        }
        this.vector = Arrays.copyOf(array, capacity);

    }

    private void extendByZeros(int nZeros) {
        double[] temp = new double[this.getSize() + nZeros];
        System.arraycopy(vector, 0, temp, 0, this.getSize());
        vector = temp;
    }

    public int getSize() {
        return vector.length;
    }

    public double getValue(int index) {
        if (index >= this.getSize() || index < 0) {
            throw new IllegalArgumentException("Выход за границы массива");
        }
        return this.vector[index];
    }

    public void setValue(int index, double value) {
        if (index >= this.getSize() || index < 0) {
            throw new IllegalArgumentException("Выход за границы массива");
        }
        this.vector[index] = value;
    }

    private void sizeEqualization(Vector v) {
        int sizeDiff = this.getSize() - v.getSize();
        if (sizeDiff < 0) {
            this.extendByZeros(-sizeDiff);
        }
    }

    public void add(Vector v) {
        if (this.getSize() < v.getSize()) {
            sizeEqualization(v);
        }
        for (int i = 0; i < v.getSize(); i++) {
            vector[i] += v.getValue(i);
        }
    }

    public void subtract(Vector v) {
        if (this.getSize() < v.getSize()) {
            sizeEqualization(v);
        }
        for (int i = 0; i < v.getSize(); i++) {
            vector[i] -= v.getValue(i);
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < this.getSize(); i++) {
            vector[i] *= number;
        }
    }

    public void turn() {
        this.multiply(-1);
    }

    public double getLength() {
        double length = 0;
        for (int i = 0; i < vector.length; i++) {
            length += Math.pow(this.getValue(i), 2);
        }
        return Math.sqrt(length);
    }

    public static Vector addition(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.add(v2);
        return v;
    }

    public static Vector subtraction(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.subtract(v2);
        return v;
    }

    public static double dotProduct(Vector v1, Vector v2) {
        double sum = 0;
        int minSize = Math.min(v1.getSize(), v2.getSize());
        for (int i = 0; i < minSize; i++) {
            sum += v1.getValue(i) * v2.getValue(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < this.getSize() - 1; i++) {
            builder.append(vector[i]).append(", ");
        }
        builder.append(vector[this.getSize() - 1]).append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) o;
        if (v.getSize() != this.getSize()) {
            return false;
        }
        for (int i = 0; i < this.getSize(); i++) {
            if ((this.vector[i] - v.vector[i]) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
    }
}
