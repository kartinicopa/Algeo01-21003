package com.matriksgeneral;

public class Adjoint {
    public static float[][] adjoint(float matriks[][], int ord) {
        float adj[][] = new float[ord-1][ord-1];

        for (int i = 0; i<ord; i++){
            for (int j = 0; j<ord; j++){
                adj[i][j] = (float) Kofaktor.kofak(matriks, i, j, ord);
            }
        }

        return (Transpose.transpose(adj, ord));
    }
}
