package com.matriksgeneral;

public class Adjoint {
    public static double[][] adjoint(double matriks[][], int ord) {
        // mengembalikan adjoint matriks

        double adj[][] = new double[ord][ord];

        for (int i = 0; i < ord; i++) {
            for (int j = 0; j < ord; j++) {
                adj[i][j] = (double) Kofaktor.kofaktor(matriks, i, j, ord); // nilai elemen Aij = kofaktor dari Aij
            }
        }

        return (Transpose.transpose(adj, ord));
    }
}
