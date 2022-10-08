package com.menu;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

import com.determinan.*;
import com.spl.*;
import com.regresi.LinearBerganda;

// awkoawkoakoa
public class Input {

    static Scanner in = new Scanner(System.in);

    public static void olahFile(String x) {
        try {
            System.out.println(x);
            System.out.println();
            int M, N;
            float m[][] = new float[100][100];
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = "test\\input\\";
            fileName += scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            System.out.println(fileName);

            if (reader.next().equals("BARIS")) {
                M = reader.nextInt();
            } else {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                return;
            }
            if (reader.next().equals("KOLOM")) {
                N = reader.nextInt();
            } else {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                return;
            }

            try {
                for (int Brs = 0; Brs < M; Brs++) {
                    for (int Kol = 0; Kol < N; Kol++) {
                        m[Brs][Kol] = reader.nextFloat();
                    }
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                return;
            }
            reader.close();
            System.out.println();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");
            scan.nextLine();
            float a[][] = new float[M][N - 1];
            float b[] = new float[M];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N - 1; j++) {
                    a[i][j] = m[i][j];
                }
                b[i] = m[i][N - 1];
            }

            if (x == "METODE ELIMINASI GAUSS") {
                Gauss.ans(a, b, M, N - 1);
            } else if (x == "METODE ELIMINASI GAUSS-JORDAN") {
                GaussJordan.ans(a, b, M, N - 1);
            } else if (x == "METODE MATRIKS BALIKAN") {
                MatriksBalikan.main();
            } else if (x == "KAIDAH CRAMER") {
                Cramer.ans(a, b, N - 1);
            } else if (x == "REGRESI") {
                LinearBerganda.ans(a, b, M, N - 1);
            }
        } catch (Exception i) {
            System.out.println();
            System.out.println("Tidak dapat membaca dari file eksternal.");
            in.nextLine();
        }
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
                matriks[i][j] = in.nextFloat();
            }
        }

        if (x == "METODE REDUKSI BARIS") {
            ReduksiBaris.ans(matriks, N);
        } else if (x == "EKSPANSI KOFAKTOR") {
            EkspansiKofaktor.ans(matriks, N);
        }
    }

    public static void spl(String x, int pilihan) {
        int M, N;
        System.out.println(x);
        System.out.println();

        // Jika kaidah cramer, yang diminta input hanyalah N
        if (x == "KAIDAH CRAMER") {
            System.out.print("Banyaknya Persamaan dan Variabel (n) = ");
            N = in.nextInt();
            M = N;
        } else {
            System.out.print("Banyaknya Persamaan (m) = ");
            M = in.nextInt();
            System.out.print("Banyaknya Variabel (n) = ");
            N = in.nextInt();
        }

        float a[][] = new float[M][N];
        float b[] = new float[M];

        // Kondisi Input Ax = B
        if (pilihan == 1) {

            System.out.println("Masukkan matriks A : ");
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    a[i][j] = in.nextFloat();
                }
            }
            System.out.println("Masukkan matriks B : ");
            for (int i = 0; i < M; i++) {
                b[i] = in.nextFloat();
            }
        } else

        // Input Matriks Augmented
        if (pilihan == 2) {
            System.out.println("Masukkan matriks augmented : ");
            for (int i = 0; i < M; i++) {
                for (int j = 0; j <= N; j++) {
                    if (j == N) {
                        b[i] = in.nextFloat();
                    } else {
                        a[i][j] = in.nextFloat();
                    }
                }
            }
        } else

        // Input Manual SPL
        if (pilihan == 3) {
            for (int i = 0; i < M; i++) {
                System.out.printf("Persamaan %d : \n", i + 1);
                System.out.println("----------------");
                for (int j = 0; j < N; j++) {
                    System.out.printf("x[%d] = ", j + 1);
                    a[i][j] = in.nextFloat();
                }
                System.out.printf("Solusi = ");
                b[i] = in.nextFloat();
            }
        }

        // System.out.println("\nA : ");
        // for (int j = 0; j < M; j++) {
        // for (int k = 0; k < N; k++) {
        // System.out.printf("%f ", a[j][k]);
        // }
        // System.out.println();
        // }
        System.out.println();
        if (x == "METODE ELIMINASI GAUSS") {
            Gauss.ans(a, b, M, N);
        } else if (x == "METODE ELIMINASI GAUSS-JORDAN") {
            GaussJordan.ans(a, b, M, N);
        } else if (x == "METODE MATRIKS BALIKAN") {
            MatriksBalikan.main();
        } else if (x == "KAIDAH CRAMER") {
            Cramer.ans(a, b, N);
        } else if (x == "REGRESI") {
            LinearBerganda.ans(a, b, M, N);
        }

    }

}
