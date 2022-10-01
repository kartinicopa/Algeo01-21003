package com.matriksgeneral;

public class Transpose {
    public static float[][] transpose (float matriks[][], int ord){
        // mengembalikan transpose matriks

        float temp[][] = matriks;
        for (int i = 0; i < ord; i++){
            for (int j = 0; j < ord; j++){
                temp[i][j] = matriks[j][i];     // baris dan kolom ditukar
            }
        }
        return temp;
    }
}
