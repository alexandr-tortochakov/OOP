package ru.academit.tortochakov.matrix;

import ru.academit.tortochakov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        rows = new Vector[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        rows = matrix.rows.clone();
    }

    public Matrix(Vector[] vectors) {
        rows = vectors.clone();
    }

    public Matrix(double[][] array) {
        rows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public int getHeight() {
        return rows.length;
    }

    public int getWidth() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        rows[index] = vector;
    }

    private void setValue(int i, int j, double value) {
        rows[i].setValue(j, value);
    }

    public Vector getColumn(int index) {
        Vector vector = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            vector.setValue(i, rows[i].getValue(index));
        }
        return vector;
    }

    public Matrix transpose() {
        Vector[] vectors = new Vector[getWidth()];
        for (int i = 0; i < getWidth(); i++) {
            vectors[i] = getColumn(i);
        }
        return new Matrix(vectors);
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < rows.length - 1; i++) {
            builder.append(rows[i].toString()).append(", ");
        }
        builder.append(rows[rows.length - 1]).append("}");
        return builder.toString();
    }

    public void add(Matrix matrix) {
        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public Vector MatrixVectorMultiplication(Vector vector) {
        Vector vector1 = new Vector(getHeight());
        for (int i = 0; i < getHeight(); i++) {
            vector1.setValue(i, Vector.dotProduct(getRow(i), vector));
        }
        return vector1;
    }

    public Matrix getSubMatrix(int i, int j) {
        Vector[] vectors = new Vector[rows.length - 1];
        for (int k = 0; k < i; k++) {
            vectors[k] = new Vector(rows[k]);
        }
        for (int k = i+1; k < rows.length; k++) {
            vectors[k - 1] = new Vector(rows[k]);
        }
        for (Vector vector : vectors) {
            vector.remove(j);
        }
        return new Matrix(vectors);
    }

    public double getDet() {
        if (getHeight() == 1 && getWidth() == 1) {
            return rows[0].getValue(0);
        }
        double det = 0;
        for (int i = 0; i < getWidth(); i++) {
            det = det + rows[0].getValue(i) * Math.pow(-1, 2 + i) * getSubMatrix(0, i).getDet();
            Matrix matrix = getSubMatrix(0, i);
        }
        return det;
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);
        return matrix;
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);
        return matrix;
    }

    public static Matrix MatrixMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        Vector[] vectors = new Vector[matrix1.getHeight()];
        for (int i = 0; i < matrix1.getHeight(); i++) {
            for (int j = 0; j < matrix2.getWidth(); j++) {
                vectors[i].setValue(j, Vector.dotProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }
        return new Matrix(vectors);
    }
}
