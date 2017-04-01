package ru.academit.tortochakov.matrix;
import ru.academit.tortochakov.vector.Vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    @Test
    public void testCopyConstructor() throws Exception {
        Vector[] test = new Vector[3];
        test[0] = new Vector(new double[]{3, 5, 6, 7});
        test[1] = new Vector(new double[]{4, 5, 6, -2});
        test[2] = new Vector(new double[]{1, 5, -5, 3});
        Matrix matrix1 = new Matrix(test);
        Matrix matrix2 = new Matrix(matrix1);
        matrix2.multiplyByScalar(3);
        assertNotEquals(matrix1, matrix2);
    }

    @Test
    public void testMultiplyByScalar() throws Exception {
        Vector[] test = new Vector[2];
        test[0] = new Vector(new double[]{3, 5});
        test[1] = new Vector(new double[]{4, 5});
        Matrix matrix1 = new Matrix(test);

        test[0] = new Vector(new double[]{6, 10});
        test[1] = new Vector(new double[]{8, 10});
        Matrix matrix2 = new Matrix(test);
        matrix1.multiplyByScalar(2);
        System.out.println(matrix1.toString());
        assertEquals(matrix1, matrix2);
    }

    @Test
    public void testGetDet() throws Exception {
        Vector[] test = new Vector[4];
        test[0] = new Vector(new double[]{2, 3, -3, 4});
        test[1] = new Vector(new double[]{2, 1, -1, 2});
        test[2] = new Vector(new double[]{6, 2, 1, 0});
        test[3] = new Vector(new double[]{2, 3, 0, -5});
        Matrix matrix1 = new Matrix(test);

        assertEquals(48, matrix1.getDet());
    }
}

