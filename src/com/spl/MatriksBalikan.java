package com.spl;

import com.matriksgeneral.Kali;
import com.invers.inversMat;

public class MatriksBalikan {
    public static float[][] splInv(float[][] A, float[][] B, int ordA){
        float[][] solution = new float[ordA][1];
        float[][] invA = new float[ordA][ordA];

        invA = inversMat.invers(A, ordA);
        solution = Kali.multiply(invA, B, ordA, ordA, ordA, 1);

        return solution;

    }
}
