package com.matriksgeneral;

import com.determinan.EkspansiKofaktor;

public class Kofaktor {
    public static float kofak(float[][] matrix, int idxrow, int idxcol, int ord){
        float cfc[][] = new float[ord-1][ord-1];
        
        int row = 0;
        
        for (int i = 0; i<ord; i++){   
            int col = 0;    
            for (int j = 0; j<ord; j++){
                if ((i!=idxrow)||(j!=idxcol)){
                    cfc[row][col] = matrix[i][j];
                    col++;
                }
            }
            if (i!=idxrow){
                row++;
            }
        }
        return ((float) Math.pow(-1, idxrow+idxcol)*EkspansiKofaktor.ans(cfc, ord-1));
    }
}