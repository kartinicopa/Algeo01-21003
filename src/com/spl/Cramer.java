package com.spl;

import java.util.Scanner;
import com.determinan.Determinan;
import com.menu.Output;

public class Cramer {

    public static void ans(double a[][], double b[], int N) {
        System.out.println("\n---------------------\n");

        double hasil[][] = new double[N][1];
        double detA = Determinan.main(a, N);
        System.out.printf("Determinan A = %f\n\n", detA);
        if (detA == 0) {
            System.out.println("Persamaan ini tidak memiliki solusi!");
            Scanner in = new Scanner(System.in);
            in.nextLine();

            return;
        }

        for (int i = 0; i < N; i++) {
            System.out.println("-----------------");
            double temp[][] = new double[N][N];
            // Copy Matriks
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == i) {
                        temp[i][k] = b[k];
                    } else {
                        temp[j][k] = a[j][k];
                    }
                }
            }

            System.out.printf("Ax[%d] :\n", i + 1);
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    System.out.printf("%f ", temp[j][k]);
                }
                System.out.println();
            }

            double detAx = Determinan.main(temp, N);
            System.out.printf("Det Ax[%d] = %f\n", i + 1, detAx);
            System.out.printf("x[%d] = %f\n", i + 1, detAx / detA);
            System.out.println();
            hasil[i][0] = detAx / detA;
        }
        Output.displayOutput(a, N, 1, "X");
        System.out.println("\nTekan apa saja untuk keluar!");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

    public static double[][] spl(double a[][], double b[][], int N) {

        double hasil[][] = new double[N][1];
        double detA = Determinan.main(a, N);
        if (detA == 0) {

            return hasil;
        }

        for (int i = 0; i < N; i++) {
            double temp[][] = new double[N][N];
            // Copy Matriks
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == i) {
                        temp[i][k] = b[k][0];
                    } else {
                        temp[j][k] = a[j][k];
                    }
                }
            }

            double detAx = Determinan.main(temp, N);
            System.out.println();
            hasil[i][0] = detAx / detA;
        }
        return hasil;
    }
}
