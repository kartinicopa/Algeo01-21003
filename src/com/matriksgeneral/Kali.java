package com.matriksgeneral;

public class Kali {
    public static double[][] kali(double[][] A, double[][] B, int rowA, int colA, int rowB, int colB) {
        // menghasilkan matriks hasil kali A dan B, prekondisi colA==rowB

        double[][] solution = new double[rowA][colB];

        for (int row = 0; row < rowA; row++) {
            for (int col = 0; col < colB; col++) {
                double elem = 0;
                for (int i = 0; i < colA; i++) {
                    elem += A[row][i] * B[i][col];
                }
                solution[row][col] = elem;
            }
        }

        return solution;
    }
}
