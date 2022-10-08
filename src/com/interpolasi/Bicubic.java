package com.interpolasi;

import com.spl.MatriksBalikan;
import java.util.Scanner;
import com.menu.Output;

public class Bicubic {
    public static float[][] matX() {
        // Mencari matriks X berukuran 16*16 dari rumus x^i*y^j, i dan j adalah baris
        // dan kolom, x dan y adalah kombinasi [-1, 0, 1, 2]
        float X[][] = new float[16][16];

        int x = -1;
        int y = -1;
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                X[row][col] = (float) (Math.pow(x, row) * Math.pow(y, col)); // x^i*y^j
            }
            x = ((x + 1) % 3) - ((x + 1) / 3); // loop x dan y dari [-1, 2]
            y = (row / 4) - 1;
        }
        return X;
    }

    public static float[][] bcbmat(float matriks[][], int ord) {
        // mengembalikan nilai koefisien [a00, a01, a02, .., a33]

        float X[][] = new float[ord * ord][ord * ord];
        float alpha[][] = new float[ord * ord][1];
        float koef[][] = new float[ord][ord];
        float a[][];

        X = Bicubic.matX();

        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                alpha[4 * row + col][0] = matriks[row][col]; // mengubah matriks 4*4 jadi 16*1
            }
        }

        a = MatriksBalikan.matriksbalikan(X, alpha, ord * ord); // Menyelesaikan spl Xa = alpha 16 variabel [a00, a33]

        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                koef[row][col] = a[4 * row + col][0]; // mengubah matriks 16*1 jadi 4*4 a00 a00 ... ... a03
            } // a01 -> ... ... ... ...
        } // ... -> ... ... ... ...
        return koef; // a33 a30 ... ... a33
    }

    public static float InterpolationVal(float[][] matriks, double d, double e, int ord) {
        // mengembalikan nilai f(x, y) berdasarkan matriks input, x, y = [0, 1]

        float koef[][] = new float[ord][ord];
        koef = bcbmat(matriks, ord);
        float pixVal = 0;
        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                pixVal += koef[row][col] * Math.pow(d, row) * Math.pow(e, col);
            }
        }
        return pixVal;
    }

    public static void ans(float[][] matriks, int N) {
        // matriks = new float[4][4];
        // for (int i = 1; i < 16; i++) {
        // matriks[(i - 1) / 4][(i - 1) % 4] = i;
        // }
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan x: ");
        float x = in.nextFloat();
        System.out.println("Masukkan y: ");
        float y = in.nextFloat();
        float pixval = InterpolationVal(matriks, x, y, 4);
        System.out.printf("f(%.2f,%.2f) = %f", x, y, pixval);
        float ans[][] = { { x, y, pixval } };
        Output.displayOutput(ans, 1, 3, "Bicubic");
    }
}
