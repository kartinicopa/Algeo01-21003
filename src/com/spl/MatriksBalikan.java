package com.spl;

import com.matriksgeneral.Kali;
import com.invers.inversMat;
import java.util.Scanner;

public class MatriksBalikan {
    public static float[][] matriksbalikan(float[][] A, float[][] B, int ordA) {
        // menyelesaikan spl Ax = B, prekondisi A[ordA][ordA], B[ordA][1]
        float[][] solution = new float[ordA][1];
        float[][] invA = new float[ordA][ordA];

        invA = inversMat.inversmat(A, ordA);
        solution = Kali.kali(invA, B, ordA, ordA, ordA, 1); // x = A^-1*B

        return solution; // bentuknya matriks berukuran [ordA][1] / [[x1], [x2], .., [xn]]

    }

    public static void ans(float[][] A, float[] B, int ordA) {
        float b[][] = new float[ordA][0];
        float jwb[][] = matriksbalikan(A, b, ordA);
        for (int i = 0; i < ordA; i++) {
            System.out.printf("x[%d] = %f\n", i + 1, jwb[i][0]);
        }

        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
