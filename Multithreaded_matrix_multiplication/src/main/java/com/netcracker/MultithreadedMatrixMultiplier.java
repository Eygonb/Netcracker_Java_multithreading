package com.netcracker;

import java.util.ArrayList;
import java.util.List;

public class MultithreadedMatrixMultiplier {

    /**
     * Waits for all threads to die in this list and clears this list.
     *
     * @param threads list of threads
     */
    private static void waitAndClearThreadsList(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }

    /**
     * Multiply multithreaded matrices
     *
     * @param firstMatrix  double dimensional array of doubles
     * @param secondMatrix double dimensional array of doubles
     * @return result       result of multiplication
     */
    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new IllegalArgumentException("The number of columns in firstMatrix isn't equal the number of rows in secondMatrix");
        }

        double[][] resultMatrix = new double[firstMatrix.length][secondMatrix[0].length];
        List<Thread> threadsList = new ArrayList<>();
        int availableProcessorsNum = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < firstMatrix.length; i++) {
            if (threadsList.size() % availableProcessorsNum == 0) {
                waitAndClearThreadsList(threadsList);
            }

            Thread thread = new Thread(new RowMultiplier(firstMatrix, secondMatrix, i, resultMatrix));
            thread.start();

            threadsList.add(thread);
        }

        return resultMatrix;
    }
}
