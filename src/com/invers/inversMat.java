package com.invers;

import com.determinan.EkspansiKofaktor;
import com.matriksgeneral.Adjoint;

public class inversMat {
    public static float[][] inversmat(float[][] matriks, int ord) {
        // mengembalikan invers dari matriks input, A^-1 = 1/det(A)*adj(A)
        
        float inv[][] = new float[ord][ord];
        float det;

        inv = Adjoint.adjoint(matriks, ord);            //Adjoint matriks
        det = EkspansiKofaktor.ans(matriks, ord);       //Determinan matriks
        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                inv[row][col] /= det;                   //Nilai di adjoint a dibagi dengan determinan
            }
        }
        return inv;
    }
}
