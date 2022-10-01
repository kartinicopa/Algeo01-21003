package com.invers;

import com.determinan.EkspansiKofaktor;
import com.matriksgeneral.Adjoint;

public class inversMat {
    public static float[][] inversmat(float[][] matriks, int ord) {
        float inv[][] = new float[ord][ord];
        float det;

        inv = Adjoint.adjoint(matriks, ord);
        det = EkspansiKofaktor.ans(matriks, ord);
        for (int row = 0; row<ord; row++){
            for (int col = 0; col<ord; col++){
                inv[row][col] /= det;
            }
        }
        return inv;
    }
}
