package com.determinan;

import java.util.*;

public class ReduksiBaris {
    static Scanner in = new Scanner(System.in);

    public static void displayMatriks(float matriks[][], int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 0)
                    System.out.print(" ");
                System.out.print(matriks[i][j]);
            }
            System.out.println();
        }
    }

    public static void ans(float matriks[][], int N) {

    }
}