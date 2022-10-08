package com.interpolasi;

import com.menu.Output;
import com.spl.GaussJordan;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Polinom {
    public static double ans(double[] k, double x) {
        // Mengembalikan hasil estimasi dari input x

        int row = k.length;
        double res = 0;

        for (int i = 0; i < row; i++) {
            res += (Math.pow(x, i) * k[i]);
        }

        return res;
    }

    public static void ans(double[][] matrix, int N) {
        // Mengembalikan koefisien dari persamaan polinomial hasil interpolasi

        int BrsEff = N;
        int KolEff = N + 1;
        double[][] poli = new double[BrsEff][KolEff];
        for (int i = 0; i < BrsEff; i++) {
            for (int j = 0; j < KolEff; j++) {
                if (j == (KolEff - 1)) {
                    poli[i][j] = matrix[i][1];
                } else if (j == 0) {
                    poli[i][0] = 1;
                } else {
                    poli[i][j] = Math.pow(matrix[i][0], j);
                }
            }

        }

        double[][] m = GaussJordan.gaussJordanElimination(poli, BrsEff, KolEff);

        double[] b = new double[BrsEff];
        for (int i = 0; i < BrsEff; i++) {
            b[i] = m[i][KolEff - 1];
        }

        boolean first = true;

        System.out.println("Persamaan polinomial : ");

        System.out.print("y =");
        for (int i = b.length - 1; i >= 0; i--) {
            if (b[i] != 0) {
                if (first)
                    first = false;
                else if (b[i] > 0 && !first)
                    System.out.print(" +");
                System.out.printf(" %f", b[i]);
                if (i != 0)
                    System.out.printf(" x^%d", i);

            }
        }
        System.out.println();

        double B[][] = { b };

        Output.displayOutput(B, 1, b.length, "Polinomial");

        System.out.println();

    }
}
