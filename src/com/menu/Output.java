package com.menu;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Output {

    public static void writeToFile(int N, int M, float mtx[][], String varr) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = "test\\output\\";
            fileName += scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            if (varr == null) {
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

    public static void displayOutput(float[][] matriks, int M, int N, String varr) {
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
