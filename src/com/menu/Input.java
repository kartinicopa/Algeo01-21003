package com.menu;

import java.util.Scanner;
// import java.io.File;
// import java.io.FileNotFoundException;

import com.determinan.*;
import com.spl.Cramer;
//import com.spl.*;
import com.spl.Gauss;
import com.spl.GaussJordan;
import com.spl.MatriksBalikan;

// awkoawkoakoa
public class Input {

    static Scanner in = new Scanner(System.in);

    public static void olahFile(String x) {

        // System.out.println(x);
        // System.out.println();

        // System.out.println("-------------------------------------------------");
        // System.out.println("| Note ! |");
        // System.out.println("| Format Masukan : <nama_file>.<ekstensi file> |");
        // System.out.println("| File harus disimpan dalam folder 'test' |");
        // System.out.println("-------------------------------------------------");
        // System.out.println("Masukkan nama file : ");
        // String fileName = in.nextLine();

        // try {
        // int M, N;
        // File myFile = new File(fileName);
        // Scanner fileReader = new Scanner(myFile);

        // // cetak isi file
        // while (fileReader.hasNextLine()) {
        // M += 1;
        // }
        // } catch (FileNotFoundException e) {
        // System.out.println("Terjadi Kesalahan: " + e.getMessage());
        // e.printStackTrace();
        // }
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
            Gauss.main();
        } else if (x == "METODE ELIMINASI GAUSS-JORDAN") {
            GaussJordan.main();
        } else if (x == "METODE MATRIKS BALIKAN") {
            MatriksBalikan.main();
        } else {
            Cramer.ans(a, b, N);
        }

    }

}
