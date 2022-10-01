package com.interpolasi;

import com.invers.inversMat;
import com.spl.MatriksBalikan;

public class Bicubic {
    public static float[][] matX(){
        float X[][] = new float[16][16];

        int x = -1;
        int y = -1;
        for (int row = 0; row < 16; row++){
            for (int col = 0; col < 16; col++){
                X[row][col] = (float) (Math.pow(x, row)*Math.pow(y, col));
            }
            x = ((x+1)%3)-((x+1)/3);
            y = (row/4)-1;
        }
        return X;
    }
    
    public static float[][] bcbmat(float matriks[][], int ord){
        float X[][] = new float[ord*ord][ord*ord];
        float alpha[][] = new float[ord*ord][1];
        float koef[][] = new float[ord][ord];
        float a[][];


        X = Bicubic.matX();
        X = inversMat.invers(X, ord*ord);
        
        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                alpha[4*row+col][0] = matriks[row][col];
            }
        }

        
        a = MatriksBalikan.splInv(X, alpha, ord*ord);

        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                koef[row][col] = a[4*row+col][0];
            }
        }
        return koef;
    }

    public static float pixelVal(float[][] matriks, int x, int y, int ord){
        float koef[][] = new float[ord][ord];
        koef = bcbmat(matriks, ord);
        float pixVal = 0;
        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                pixVal += koef[row][col]*Math.pow(x, row)*Math.pow(y, col);
            }
        }
        return pixVal;
    }

    public static void main() {

    }
}
