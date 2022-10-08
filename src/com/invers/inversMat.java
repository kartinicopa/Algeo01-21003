package com.invers;

import java.util.Scanner;
import com.determinan.EkspansiKofaktor;
import com.matriksgeneral.Adjoint;
import com.menu.Output;

public class inversMat {
    public static double[][] inversmat(double[][] matriks, int ord) {
        // mengembalikan invers dari matriks input, A^-1 = 1/det(A)*adj(A)

        double inv[][] = new double[ord][ord];
        double det;

        inv = Adjoint.adjoint(matriks, ord); // Adjoint matriks
        det = EkspansiKofaktor.det(matriks, ord); // Determinan matriks
        for (int row = 0; row < ord; row++) {
            for (int col = 0; col < ord; col++) {
                inv[row][col] /= det; // Nilai di adjoint a dibagi dengan determinan
            }
        }
        return inv;
    }

    public static void ans(double[][] matriks, int ord) {
        Scanner in = new Scanner(System.in);

        matriks = inversmat(matriks, ord);
        System.out.println("Matriks setelah operasi matriks balikan : ");
        in.nextLine();
        for (int j = 0; j < ord; j++) {
            for (int k = 0; k < ord; k++) {
                System.out.printf("%f ", matriks[j][k]);
            }
            System.out.println();
        }
        in.nextLine();
        Output.displayOutput(matriks, ord, ord, null);
        System.out.println("\nTekan apa saja untuk keluar!");

        in.nextLine();
    }
}
