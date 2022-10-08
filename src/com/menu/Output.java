package com.menu;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Output {

    public static void writeToFile(int N, int M, double mtx[][], String varr) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = "test\\output\\";
            fileName += scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            if (varr == "Polinomial") {
                boolean first = true;
                writer.print("y =");
                for (int i = N - 1; i >= 0; i--) {
                    if (mtx[0][i] != 0) {
                        if (first)
                            first = false;
                        else if (mtx[0][i] > 0 && !first)
                            writer.print(" +");
                        writer.printf(" %f", mtx[0][i]);
                        if (i != 0)
                            writer.printf(" x^%d", i);

                    }
                }
            } else if (varr == "Bicubic") {
                writer.printf("f(%.2f,%.2f) = %f", mtx[0][0], mtx[0][1], mtx[0][2]);
            } else if (varr == "Regresi") {
                writer.print("y = ");
                for (int i = 0; i < M; i++) {
                    if (i != 0)
                        writer.print("+ ");
                    writer.format("(%f)x%d ", mtx[i][0], +1);
                }
                writer.println();
                for (int i = 0; i < M; i++) {
                    writer.format("X%d = %f\n", +1, mtx[i][1]);
                }
                writer.format("Hasil = %f", mtx[M - 1][0]);
            } else if (varr == "Determinan") {
                writer.format("Determinan = %.3f\n", mtx[0][0]);
            } else if (varr == null) {
                if ((M == 0) && (N == 0)) {
                    writer.println("Matriks kosong.");
                }
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < N; j++) {
                        writer.format("%.3f", mtx[i][j]);
                        writer.print(" ");
                        if (j == (N - 2)) {
                            writer.print("| ");
                        }
                    }
                    writer.print("\n");
                }
            } else {
                if ((M == 0) && (N == 0)) {
                    writer.println("Persamaan ini tidak memiliki solusi");
                } else {
                    writer.println("Sistem persamaan memiliki solusi unik:");
                    for (int i = 0; i < M; i++) {

                        for (int j = 0; j < N; j++) {
                            writer.format("%s%d = %.3f", varr, j + 1, mtx[i][j]);
                            writer.print(" ");
                            if (j == (N - 2)) {
                                writer.print("| ");
                            }
                        }
                        writer.print("\n");
                    }
                }

            }

            writer.close();
            System.out.println();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");

            scan.nextLine();
        } catch (IOException e) {
            System.out.println();
            System.out.println("Tidak dapat menulis ke file eksternal.");
            Scanner scan = new Scanner(System.in);
            scan.nextLine();
        }
    }

    public static void displayOutput(double[][] matriks, int M, int N, String varr) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nApakah ingin menyimpan output ke dalam file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.println("------------------");
        int pil = scan.nextInt();

        if (pil == 1) {
            writeToFile(N, M, matriks, varr);
        }
    }
}
