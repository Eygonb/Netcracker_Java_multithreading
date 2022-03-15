package com.netcracker;

class RowMultiplier implements Runnable {
    private final double[][] firstMatrix;
    private final double[][] secondMatrix;
    private final int rowNum;
    private final double[][] resultMatrix;

    public RowMultiplier(double[][] firstMatrix, double[][] secondMatrix, int rowNum, double[][] resultMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.rowNum = rowNum;
        this.resultMatrix = resultMatrix;
    }

    /**
     * Multiply specified row of two matrices.
     */
    @Override
    public void run() {
        for (int i = 0; i < secondMatrix[0].length; i++) {
            resultMatrix[rowNum][i] = 0;
            for (int j = 0; j < firstMatrix[rowNum].length; j++) {
                resultMatrix[rowNum][i] += firstMatrix[rowNum][j] * secondMatrix[j][i];
            }
        }
    }
}
