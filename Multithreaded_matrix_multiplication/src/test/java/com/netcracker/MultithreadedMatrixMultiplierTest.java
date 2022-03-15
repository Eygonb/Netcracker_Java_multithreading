package com.netcracker;

import org.junit.Assert;
import org.junit.Test;

public class MultithreadedMatrixMultiplierTest {
    @Test
    public void correctMultiplyTest() {
        double[][] m1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] m2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        double[][] result = MultithreadedMatrixMultiplier.multiply(m1, m2);

        Assert.assertArrayEquals(new double[][]{{30, 24, 18}, {84, 69, 54}}, result);
    }

    @Test
    public void incorrectMultiplyTest() {
        double[][] m1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] m2 = {{9, 8, 7}, {6, 5, 4}};

        Assert.assertThrows(IllegalArgumentException.class, () -> MultithreadedMatrixMultiplier.multiply(m1, m2));
    }

}
