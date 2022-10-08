package com.regresi;

import java.io.*;
import java.util.*;
import com.menu.Output;
import com.spl.GaussJordan;

public class LinearBerganda {
    static Scanner sc = new Scanner(System.in);

    public static void displayMat(float[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
            ;
        }
    }

    public static float[] solveRegresi(float[][] matrix, float[] y) {
        // buat matriks augmented [1|matrix|y] : temp
        int row = matrix.length;
        int col = matrix[0].length;
        float[][] temp = new float[row][col + 2];

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
        float[][] spl = new float[col + 1][col + 2];
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
        float[][] res = GaussJordan.gaussJordanElimination(spl, col + 1, col + 2);

        // Mengambil nilai y saja
        float[] b = new float[col + 1];
        for (int i = 0; i <= col; i++) {
            b[i] = res[i][col + 1];
        }

        return b;
    }

    public static void ans(float[][] mat, float y[], int m, int n) {
        // Menyelesaikan regresi linear berganda
        float[] res = solveRegresi(mat, y);

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
        float[] taksir = new float[res.length];
        for (int i = 0; i < res.length - 1; i++) {
            taksir[i] = sc.nextFloat();
        }
        float result = res[0];
        for (int i = 0; i < res.length - 1; i++) {
            result += res[i + 1] * taksir[i];
        }
        System.out.printf("Nilai taksirannya adalah %f\n\n", result);

        // Memilih menyimpan di file atau tidak
        float Result[][] = new float[res.length][1];
        for (int i = 0; i < res.length; i++) {
            Result[i][0] = res[i];
        }
        Output.displayOutput(Result, m, 1, null);
    }
}
