package ru.academit.tortochakov.vector;

public class Vector {
    private double[] array;

    public Vector(double[] array) {
        this.array = array.clone();
    }

    public Vector(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше 0");
        }
        this.array = new double[capacity];
    }

    public Vector(Vector v) {
        array = v.array.clone();
    }

    public Vector(int capacity, double[] array) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше 0");
        }
        this.array = array.clone();
        if (capacity > this.getSize()) {
            this.extendByZeros(capacity - this.getSize());
        }
    }

    private void extendByZeros(int nZeros) {
        double[] temp = new double[this.getSize() + nZeros];
        for (int i = 0; i < this.getSize(); i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public int getSize() {
        return array.length;
    }

    public double getValue(int index) {
        if (index >= this.getSize()) {
            throw new IllegalArgumentException("Выход за границы массива");
        }
        return this.array[index];
    }

    public void setValue(int index, double value) {
        if (index >= this.getSize()) {
            throw new IllegalArgumentException("Выход за границы массива");
        }
        this.array[index] = value;
    }

    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < this.getSize(); i++) {
            double temp = array[i];
            result += temp + ", ";
        }
        result = result.substring(0, result.length() - 2) + "}";
        return result;
    }

    public void add(Vector v) {
        int size = v.getSize() - this.getSize();
        if (size > 0) {
            this.extendByZeros(size);
        } else if (size < 0) {
            v.extendByZeros(-size);
        }
        size = v.getSize();
        for (int i = 0; i < size; i++) {
            array[i] += v.getValue(i);
        }
    }

    public void subtract(Vector v) {
        Vector temp = new Vector(v);
        temp.turn();
        this.add(temp);
    }

    public void multiply(double number) {
        int size = this.getSize();
        for (int i = 0; i < size; i++) {
            array[i] *= number;
        }
    }

    public void turn() {
        this.multiply(-1);
    }

    public double getLength() {
        double length = 0;
        for (int i = 0; i < array.length; i++) {
            double temp = Math.pow(this.getValue(i), 2);
            length += temp;
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
        for (int i = 0; i < Math.min(v1.getSize(), v2.getSize()); i++) {
            sum += v1.getValue(i) * v2.getValue(i);
        }
        return sum;
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
            if (Math.abs(this.array[i] - v.array[i]) > 0.0001) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        final int PRIME = 37;
        for (int i = 0; i < this.getSize(); i++) {
            hash = hash * PRIME + (int) array[i];
        }
        return hash;
    }
}
