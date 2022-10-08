package com.determinan;

import com.matriksgeneral.Kofaktor;
import com.menu.Output;

public class EkspansiKofaktor {
    public static double det(double matriks[][], int ord) {
        double det;

        if (ord == 1) {
            return matriks[0][0];
        } else {
            det = 0;
            for (int i = 0; i < ord; i++) {
                det += matriks[0][i] * Kofaktor.kofaktor(matriks, 0, i, ord);
            }
            return det;
        }

    }

    public static void ans(double matriks[][], int ord) {
        double ans = det(matriks, ord);
        System.out.printf("Determinan = %f", ans);
        System.out.println();
        double jwb[][] = new double[1][1];
        jwb[0][0] = ans;
        Output.displayOutput(jwb, 1, 1, "Determinan");
    }
}