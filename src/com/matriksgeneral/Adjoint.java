package com.matriksgeneral;

public class Adjoint {
    public static float[][] adjoint(float matriks[][], int ord) {
        // mengembalikan adjoint matriks

        float adj[][] = new float[ord][ord];

        for (int i = 0; i<ord; i++){
            for (int j = 0; j<ord; j++){
                adj[i][j] = (float) Kofaktor.kofaktor(matriks, i, j, ord);  //nilai elemen Aij = kofaktor dari Aij
            }
        }

        return (Transpose.transpose(adj, ord));
    }
}
