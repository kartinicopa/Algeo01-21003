package com.spl;

import com.matriksgeneral.Kali;
import com.invers.inversMat;

public class MatriksBalikan {
    public static float[][] matriksbalikan(float[][] A, float[][] B, int ordA){
        // menyelesaikan spl Ax = B, prekondisi A[ordA][ordA], B[ordA][1]
        float[][] solution = new float[ordA][1];
        float[][] invA = new float[ordA][ordA];

        invA = inversMat.inversmat(A, ordA);                            
        solution = Kali.kali(invA, B, ordA, ordA, ordA, 1);     // x = A^-1*B

        return solution;            // bentuknya matriks berukuran [ordA][1] / [[x1], [x2], .., [xn]]

    }
}
