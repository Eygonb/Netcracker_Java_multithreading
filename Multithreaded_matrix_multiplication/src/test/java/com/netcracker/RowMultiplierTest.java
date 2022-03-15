package com.netcracker;

import org.junit.Assert;
import org.junit.Test;

public class RowMultiplierTest {
    @Test
    public void multiplyTest() {
        double[][] m1 = {{1, 2, 3}, {4, 5, 6}};
        double[][] m2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        double[][] result = new double[m1.length][m2[0].length];

        RowMultiplier rowMultiplier = new RowMultiplier(m1, m2, 0, result);
        rowMultiplier.run();

        Assert.assertArrayEquals(new double[][]{{30, 24, 18}, {0, 0, 0}}, result);

        result = new double[m1.length][m2[0].length];

        rowMultiplier = new RowMultiplier(m1, m2, 1, result);
        rowMultiplier.run();

        Assert.assertArrayEquals(new double[][]{{0, 0, 0}, {84, 69, 54}}, result);

        rowMultiplier = new RowMultiplier(m1, m2, 0, result);
        rowMultiplier.run();

        Assert.assertArrayEquals(new double[][]{{30, 24, 18}, {84, 69, 54}}, result);
    }
}
