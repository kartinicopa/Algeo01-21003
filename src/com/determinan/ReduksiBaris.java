package com.determinan;

import java.util.Scanner;

public class ReduksiBaris {

    static float m[][];
    static int N;

    public static void displayMatriks(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 0)
                    System.out.print(" ");
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }

    public static void garis() {
        System.out.println("\n------------------");
    }

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

    public static float determinan() {
        System.out.println();
        float det = 1;
        for (int i = 0; i < N; i++) {
            garis();

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
            displayMatriks(N);
        }
        return det;
    }

    public static void ans(float matriks[][], int n) {
        N = n;
        m = matriks;

        float det = determinan();
        System.out.printf("\nDeterminan = %f\n", det);

        System.out.println("\nTekan apa saja untuk keluar!");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

}