package com.determinan;

public class Determinan {
    static int N;

    public static void obe(float m[][], int idx) {
        for (int i = idx + 1; i < N; i++) {
            float k = m[i][idx] / m[idx][idx];
            m[i][idx] = 0;
            for (int j = idx + 1; j < N; j++) {
                m[i][j] -= k * m[idx][j];
            }
        }
    }

    public static boolean bisaDitukar(float m[][], int idx) {
        for (int i = idx + 1; i < N; i++) {
            if (m[i][idx] != 0) {
                // Swap
                float temp[] = m[i];
                m[i] = m[idx];
                m[idx] = temp;
                return true;
            }
        }
        return false;
    }

    public static float main(float matriks[][], int n) {
        float m[][] = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] += matriks[i][j];
            }
        }
        N = n;
        // System.out.println("\nA : ");
        // for (int j = 0; j < N; j++) {
        // for (int k = 0; k < N; k++) {
        // System.out.printf("%f ", matriks[j][k]);
        // }
        // System.out.println();
        // }

        // System.out.println("\nA : ");
        // for (int j = 0; j < n; j++) {
        // for (int k = 0; k < n; k++) {
        // System.out.printf("%f ", m[j][k]);
        // }
        // System.out.println();
        // }
        float det = 1;
        for (int i = 0; i < N; i++) {
            // Jika diagonal ke-i 0
            if (m[i][i] == 0) {
                if (bisaDitukar(m, i) == false) {
                    return 0;
                } else {
                    det *= -1;
                }
            }
            det *= m[i][i];
            obe(m, i);
        }
        return det;
    }
}
