package com.determinan;

public class Determinan {
    static float m[][];
    static int N;

    public static void obe(int idx) {
        for (int i = idx + 1; i < N; i++) {
            float k = m[i][idx] / m[idx][idx];
            m[i][idx] = 0;
            for (int j = idx + 1; j < N; j++) {
                m[i][j] -= k * m[idx][j];
            }
        }
    }

    public static boolean bisaDitukar(int idx) {
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
        m = matriks;
        N = n;

        float det = 1;
        for (int i = 0; i < N; i++) {
            // Jika diagonal ke-i 0
            if (m[i][i] == 0) {
                if (bisaDitukar(i) == false) {
                    System.out.printf("Elemen diagonal baris ke-%d adalah 0\n", i + 1);
                    return 0;
                } else {
                    det *= -1;
                }
            }
            det *= m[i][i];
            obe(i);
        }
        return det;
    }
}
