package com.interpolasi;

import com.invers.inversMat;
import com.spl.MatriksBalikan;

public class Bicubic {
    public static float[][] matX(){
        // Mencari matriks X berukuran 16*16 dari rumus x^i*y^j, i dan j adalah baris dan kolom, x dan y adalah kombinasi [-1, 0, 1, 2]
        float X[][] = new float[16][16];

        int x = -1;
        int y = -1;
        for (int row = 0; row < 16; row++){
            for (int col = 0; col < 16; col++){
                X[row][col] = (float) (Math.pow(x, row)*Math.pow(y, col));  //x^i*y^j
            }
            x = ((x+1)%3)-((x+1)/3);    // loop x dan y dari [-1, 2]
            y = (row/4)-1;
        }
        return X;
    }
    
    public static float[][] bcbmat(float matriks[][], int ord){
        // mengembalikan nilai koefisien [a00, a01, a02, .., a33]

        float X[][] = new float[ord*ord][ord*ord];
        float alpha[][] = new float[ord*ord][1];
        float koef[][] = new float[ord][ord];
        float a[][];


        X = Bicubic.matX();
        
        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                alpha[4*row+col][0] = matriks[row][col];    // mengubah matriks 4*4 jadi 16*1
            }
        }

        
        a = MatriksBalikan.matriksbalikan(X, alpha, ord*ord);   // Menyelesaikan spl Xa = alpha 16 variabel [a00, a33]

        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                koef[row][col] = a[4*row+col][0];       // mengubah matriks 16*1 jadi 4*4 a00      a00 ... ... a03
            }                                           //                                a01  ->  ... ... ... ...
        }                                               //                                ...  ->  ... ... ... ...
        return koef;                                    //                                a33      a30 ... ... a33
    }

    public static float InterpolationVal(float[][] matriks, int x, int y, int ord){
        // mengembalikan nilai f(x, y) berdasarkan matriks input, x, y = [0, 1]

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
