package com.determinan;

import com.matriksgeneral.Kofaktor;
import com.menu.Output;

public class EkspansiKofaktor {
    public static float det(float matriks[][], int ord) {
        float det;

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

    public static void ans(float matriks[][], int ord) {
        float ans = det(matriks, ord);
        System.out.printf("Determinan = %f", ans);
        System.out.println();
        float jwb[][] = new float[1][1];
        jwb[0][0] = ans;
        Output.displayOutput(jwb, 1, 1, "Determinan");
    }
}