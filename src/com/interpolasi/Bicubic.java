package com.interpolasi;

import java.util.Scanner;
import com.spl.*;
import java.util.Scanner;
import com.menu.Output;

public class Bicubic {

    static Scanner in = new Scanner(System.in);

    public static double[][] matX() {
        // Mencari matriks X berukuran 16*16 dari rumus x^i*y^j, i dan j adalah baris
        // dan kolom, x dan y adalah kombinasi [-1, 0, 1, 2]
        double X[][] = new double[16][16];

        int x = -1;
        int y = -1;
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                X[row][col] = (double) (Math.pow(x, row) * Math.pow(y, col)); // x^i*y^j
            }
            x = ((x + 1) % 3) - ((x + 1) / 3); // loop x dan y dari [-1, 2]
            y = (row / 4) - 1;
        }
        return X;
    }

    public static double[][] bcbmat(double matriks[][], int ord) {
        // mengembalikan nilai koefisien [a00, a01, a02, .., a33]

        double X[][] = new double[ord * ord][ord * ord];
        double alpha[][] = new double[ord * ord][1];
        double koef[][] = new double[ord][ord];
        double a[][];

        X = matX();

        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                alpha[4 * row + col][0] = matriks[row][col]; // mengubah matriks 4*4 jadi 16*1
            }
        }

        // a = MatriksBalikan.matriksbalikan(X, alpha, ord * ord); // Menyelesaikan spl
        // Xa = alpha 16 variabel [a00, a33]
        a = Cramer.spl(X, alpha, ord * ord); // Menyelesaikan spl Xa = alpha 16 variabel [a00, a33]
        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                koef[row][col] = a[4 * row + col][0]; // mengubah matriks 16*1 jadi 4*4 a00 a00 ... ... a03
            } // a01 -> ... ... ... ...
        } // ... -> ... ... ... ...
        return koef; // a33 a30 ... ... a33
    }

    public static double InterpolationVal(double[][] matriks, double d, double e, int ord) {
        // mengembalikan nilai f(x, y) berdasarkan matriks input, x, y = [0, 1]

        double koef[][] = new double[ord][ord];
        koef = bcbmat(matriks, ord);
        double pixVal = 0;
        for (int row = 0; row < ord; row++) {

            for (int col = 0; col < ord; col++) {
                pixVal += koef[row][col] * Math.pow(d, row) * Math.pow(e, col);
            }
        }
        return pixVal;
    }

    public static void ans(double[][] matriks, int N) {
        // matriks = new double[4][4];
        // for (int i = 1; i < 16; i++) {
        // matriks[(i - 1) / 4][(i - 1) % 4] = i;
        // }
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan x: ");
        double x = in.nextDouble();
        System.out.println("Masukkan y: ");
        double y = in.nextDouble();
        System.out.println();
        double pixval = InterpolationVal(matriks, x, y, 4);
        System.out.printf("f(%.2f,%.2f) = %f", x, y, pixval);
        double ans[][] = { { x, y, pixval } };
        Output.displayOutput(ans, 1, 3, "Bicubic");
    }

}
