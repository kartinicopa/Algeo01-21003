package com.menu;

import java.util.Scanner;
import com.determinan.*;
import com.spl.*;

public class Input {
    static Scanner in = new Scanner(System.in);

    public static void spl(String x) {

    }

    public static void matriks(String x) {
        int N;
        System.out.println(x);
        System.out.println();
        System.out.print("Ordo Matriks (N) = ");
        N = in.nextInt();
        System.out.println();

        float matriks[][] = new float[N][N];
        System.out.println("Masukkan elemen matriks :");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matriks[i][j] = in.nextInt();
            }
        }

        if (x == "METODE REDUKSI BARIS")
            ReduksiBaris.ans(matriks, N);
        else {
            EkspansiKofaktor.ans(matriks, N);
        }
    }

}
