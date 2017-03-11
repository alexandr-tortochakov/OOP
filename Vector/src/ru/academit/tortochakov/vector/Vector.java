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
        if (capacity <= array.length) {
            this.vector = array.clone();
        } else  {
            this.vector = new double[capacity];
            for (int i = 0; i < array.length; i++) {
                vector[i] = array[i];
            }
        }
    }

    private void extendByZeros(int nZeros) {
        double[] temp = new double[this.getSize() + nZeros];
        for (int i = 0; i < this.getSize(); i++) {
            temp[i] = vector[i];
        }
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

    private Vector sizeEqualization(Vector v) {
        Vector temp = new Vector(v);
        int sizeDiff = temp.getSize() - this.getSize();
        if (sizeDiff > 0) {
            this.extendByZeros(sizeDiff);
        } else if (sizeDiff < 0) {
            temp.extendByZeros(-sizeDiff);
        }
        return temp;
    }

    public void add(Vector v) {
        Vector temp = sizeEqualization(v);
        for (int i = 0; i < temp.getSize(); i++) {
            vector[i] += temp.getValue(i);
        }
    }

    public void subtract(Vector v) {
        Vector temp = sizeEqualization(v);
        for (int i = 0; i < temp.getSize(); i++) {
            vector[i] -= temp.getValue(i);
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
            if (Math.abs(this.vector[i] - v.vector[i]) == 0) {
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
