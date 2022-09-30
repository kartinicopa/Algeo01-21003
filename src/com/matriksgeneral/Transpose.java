package com.matriksgeneral;

public class Transpose {
    public static float[][] transpose (float matriks[][], int ord){
        float temp[][] = matriks;
        for (int i = 0; i < ord; i++){
            for (int j = 0; j < ord; j++){
                temp[i][j] = matriks[j][i];
            }
        }
        return temp;
    }
}
