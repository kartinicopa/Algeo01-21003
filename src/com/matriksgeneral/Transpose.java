package com.matriksgeneral;

public class Transpose {
    public static double[][] transpose(double matriks[][], int ord) {
        // mengembalikan transpose matriks

        double temp[][] = matriks;
        for (int i = 0; i < ord; i++) {
            for (int j = 0; j < ord; j++) {
                temp[i][j] = matriks[j][i]; // baris dan kolom ditukar
            }
        }
        return temp;
    }
}
