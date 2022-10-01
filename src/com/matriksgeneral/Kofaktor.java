package com.matriksgeneral;

import com.determinan.EkspansiKofaktor;

public class Kofaktor {
    public static float kofaktor(float[][] matrix, int idxrow, int idxcol, int ord){
        // mengembalikan nilai kofaktor matriks[idxrow][idxcol], rumus : Cij = -1^(i+j)*Mij
        
        float minor[][] = new float[ord-1][ord-1];
        
        int row = 0;
        for (int i = 0; i<ord; i++){                // menghasilkan matriks minor, Mij = det(minor(i, j))   
            int col = 0;    
            for (int j = 0; j<ord; j++){
                if ((i!=idxrow)||(j!=idxcol)){
                    minor[row][col] = matrix[i][j];
                    col++;
                }
            }
            if (i!=idxrow){
                row++;
            }
        }
        return ((float) Math.pow(-1, idxrow+idxcol)*EkspansiKofaktor.ans(minor, ord-1));
    }
}