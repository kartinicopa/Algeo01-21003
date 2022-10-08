package com.invers;

import java.util.Scanner;
import com.determinan.EkspansiKofaktor;
import com.matriksgeneral.Adjoint;
import com.menu.Output;

public class inversMat {
    public static float[][] inversmat(float[][] matriks, int ord) {
        // mengembalikan invers dari matriks input, A^-1 = 1/det(A)*adj(A)

        float inv[][] = new float[ord][ord];
        float det;

        inv = Adjoint.adjoint(matriks, ord); // Adjoint matriks
        det = EkspansiKofaktor.det(matriks, ord); // Determinan matriks
        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                inv[row][col] /= det; // Nilai di adjoint a dibagi dengan determinan
            }
        }
        return inv;
    }

    public static void ans(float[][] matriks, int ord) {
        matriks = inversmat(matriks, ord);
        System.out.println("Matriks setelah operasi matriks balikan : ");
        for (int j = 0; j < ord; j++) {
            for (int k = 0; k < ord; k++) {
                System.out.printf("%f ", matriks[j][k]);
            }
            System.out.println();
        }

        Output.displayOutput(matriks, ord, ord, null);
        System.out.println("\nTekan apa saja untuk keluar!");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
