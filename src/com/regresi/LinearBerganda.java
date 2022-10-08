package com.regresi;

import java.util.*;
import com.menu.Output;
import com.spl.GaussJordan;

public class LinearBerganda {
    // static Scanner sc = new Scanner(System.in);

    public static void displayMat(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
            ;
        }
    }

    public static double[] solveRegresi(double[][] matrix, double[] y) {
        // buat matriks augmented [1|matrix|y] : temp
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] temp = new double[row][col + 2];

        // Menambahkan angka 1 di kolom pertama sebagai koefisien b0
        for (int i = 0; i < row; i++) {
            temp[i][0] = 1;
        }

        // Menyalin matrix
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                temp[i][j] = matrix[i][j - 1];
            }
        }
        // Menyalin hasil augmented
        for (int i = 0; i < row; i++) {
            temp[i][col + 1] = y[i];
        }

        // Membuat matrix SPL dengan menggunakan Normal Estimation Equation
        double[][] spl = new double[col + 1][col + 2];
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= col + 1; j++) {
                spl[i][j] = 0;
                for (int k = 0; k < row; k++) {
                    spl[i][j] += temp[k][i] * temp[k][j];
                }
            }
        }
        System.out.println("\nDengan Normal Estimation Equation, diperoleh matrix SPL sebagai berikut");
        displayMat(spl);

        // Menyelesaikan matrix SPL dengan metode gauss jordan
        double[][] res = GaussJordan.gaussJordanElimination(spl, col + 1, col + 2);

        // Mengambil nilai y saja
        double[] b = new double[col + 1];
        for (int i = 0; i <= col; i++) {
            b[i] = res[i][col + 1];
        }

        return b;
    }

    public static void ans(double[][] mat, double y[], int m, int n) {
        // Menyelesaikan regresi linear berganda
        double[] res = solveRegresi(mat, y);
        double Result[][] = new double[res.length + 1][2];
        // Mengeluarkan persamaan regresi linear berganda
        System.out.println("\nPersamaan regresi linear berganda yang diperoleh");
        System.out.printf("y = %f", res[0]);
        for (int i = 1; i < res.length; i++) {
            if (res[i] > 0) {
                System.out.printf(" + %f x%d\n", res[i], i);
            } else {
                System.out.printf(" %f x%d\n", res[i], i);
            }
        }

        // Menaksir nilai fungsi
        System.out.println("\n\nMenaksir nilai fungsi");
        System.out.printf("Masukkan %d peubah yang akan ditaksir nilai fungsinya\n", res.length - 1);
        double[] taksir = new double[res.length];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < res.length - 1; i++) {
            taksir[i] = sc.nextDouble();
        }

        for (int i = 0; i < res.length; i++) {
            Result[i][0] = res[i];
            Result[i][1] = taksir[i];
        }

        double result = res[0];
        for (int i = 0; i < res.length - 1; i++) {
            result += res[i + 1] * taksir[i];
        }
        System.out.printf("Nilai taksirannya adalah %f\n\n", result);

        Result[res.length][0] = result;
        Output.displayOutput(Result, res.length + 1, 2, "Regresi");
        Scanner in = new Scanner(System.in);
        System.out.println("\nTekan apa saja untuk keluar!");
        in.nextLine();

    }
}
