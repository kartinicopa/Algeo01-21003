package com.spl;

import com.matriksgeneral.Kali;
import com.invers.inversMat;
import java.util.Scanner;

public class MatriksBalikan {
    public static double[][] matriksbalikan(double[][] A, double[][] B, int ordA) {
        // menyelesaikan spl Ax = B, prekondisi A[ordA][ordA], B[ordA][1]
        double[][] solution = new double[ordA][1];
        double[][] invA = new double[ordA][ordA];

        invA = inversMat.inversmat(A, ordA);
        solution = Kali.kali(invA, B, ordA, ordA, ordA, 1); // x = A^-1*B

        return solution; // bentuknya matriks berukuran [ordA][1] / [[x1], [x2], .., [xn]]

    }

    public static void ans(double[][] A, double[] B, int ordA) {
        double b[][] = new double[ordA][0];
        double jwb[][] = matriksbalikan(A, b, ordA);
        for (int i = 0; i < ordA; i++) {
            System.out.printf("x[%d] = %f\n", i + 1, jwb[i][0]);
        }

        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
