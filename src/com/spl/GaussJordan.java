package com.spl;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

// EliminasiGaussJordan Class
public class GaussJordan {
    static double[][] mtx = new double[100][100];
    static int BrsEff;
    static int KolEff;

    public GaussJordan() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                mtx[i][j] = 0;
            }
        }
        BrsEff = 0;
        KolEff = 0;
    }

    public void setBrsEff(int n) {
        BrsEff = n;
    }

    public void setKolEff(int n) {
        KolEff = n;
    }

    public int getBrsEff() {
        return BrsEff;
    }

    public int getKolEff() {
        return KolEff;
    }

    private static int getMaxAbsKolIdx(int currBrs, int currKol) {
        int maxBrsIdx = currBrs;
        for (int i = currBrs; i < BrsEff; i++) {
            if (Math.abs(mtx[i][currKol]) > Math.abs(mtx[maxBrsIdx][currKol])) {
                maxBrsIdx = i;
            }
        }
        return maxBrsIdx;
    }

    private static void swapBrs(int Brs1Idx, int Brs2Idx) {
        double[] tempBrs = new double[KolEff];
        for (int j = 0; j < KolEff; j++) {
            tempBrs[j] = mtx[Brs1Idx][j];
        }
        for (int j = 0; j < KolEff; j++) {
            mtx[Brs1Idx][j] = mtx[Brs2Idx][j];
        }
        for (int j = 0; j < KolEff; j++) {
            mtx[Brs2Idx][j] = tempBrs[j];
        }

    }

    private static void Pivot(int currBrs, int currKol) {
        int pivotBrs = getMaxAbsKolIdx(currBrs, currKol);
        swapBrs(currBrs, pivotBrs);
    }

    private static void bagiBrs(int i, double Bagi) {
        for (int j = 0; j < KolEff; j++) {
            mtx[i][j] /= Bagi;
        }
    }

    private static void kurangBrs(int i, int j, double Kali) {
        for (int k = 0; k < KolEff; k++) {
            mtx[i][k] -= (mtx[j][k] * Kali);
        }
    }

    private static boolean allBarisNol(int currBrs) {
        for (int i = 0; i < KolEff; i++) {
            if (mtx[currBrs][i] != 0) {
                return false;
            }
        }
        return true;

    }

    private static boolean isUnik(int currBrs) {
        for (int i = 0; i < KolEff - 1; i++) {
            if (mtx[currBrs][i] != 0) {
                return false;
            }
        }

        if (mtx[currBrs][KolEff - 1] == 0) {
            return false;
        }

        return true;

    }

    private static boolean BarisUnik() {
        for (int i = 0; i < BrsEff; i++) {
            if (isUnik(i) == true) {
                return true;
            }
        }
        return false;

    }

    private static boolean noSolusi() {
        return (BarisUnik());
    }

    private static boolean solusiBanyak() {
        return (BrsEff < (KolEff - 1));

    }

    private static int getIdxPertama(int currBrs) {
        for (int i = 0; i < KolEff - 1; i++) {
            if (mtx[currBrs][i] == 1.0) {
                return i;
            }
        }

        return -1;

    }

    private static int getIdxBarisPertama(int IdxPertama) {
        for (int i = 0; i < BrsEff; i++) {
            if (getIdxPertama(i) == IdxPertama) {
                return i;
            }
        }
        return -1;
    }

    private static void hapusBaris(int currBrs) {
        for (int i = currBrs; i < BrsEff - 1; i++) {
            for (int j = 0; j < KolEff; j++) {
                mtx[i][j] = mtx[i + 1][j];
            }
        }
        BrsEff--;
    }

    private static void hapusBarisNol() {
        for (int i = BrsEff - 1; i >= 0; i--) {
            if (allBarisNol(i)) {
                hapusBaris(i);
            }
        }
    }

    public static void gaussJordanElimination() {
        int currBaseBrs = 0;
        int currBaseKol = 0;
        int currBrs;
        for (; (currBaseBrs < BrsEff) && (currBaseKol < KolEff - 1); currBaseBrs++) {
            Pivot(currBaseBrs, currBaseKol);
            while ((mtx[currBaseBrs][currBaseKol] == 0) && (currBaseKol < KolEff - 1)) {
                currBaseKol++;
                Pivot(currBaseBrs, currBaseKol);
            }
            if (currBaseKol > KolEff - 2) {
                break;
            }
            double pivotBagi = mtx[currBaseBrs][currBaseKol];
            bagiBrs(currBaseBrs, pivotBagi);
            for (currBrs = currBaseBrs + 1; currBrs < BrsEff; currBrs++) {
                double multiplier = mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            for (currBrs = currBaseBrs - 1; currBrs >= 0; currBrs--) {
                double multiplier = mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            currBaseKol++;

        }
    }

    public static double[][] gaussJordanElimination(double[][] m, int M, int N) {
        mtx = m;
        BrsEff = M;
        KolEff = N;

        int currBaseBrs = 0;
        int currBaseKol = 0;
        int currBrs;
        for (; (currBaseBrs < BrsEff) && (currBaseKol < KolEff - 1); currBaseBrs++) {
            Pivot(currBaseBrs, currBaseKol);
            while ((mtx[currBaseBrs][currBaseKol] == 0) && (currBaseKol < KolEff - 1)) {
                currBaseKol++;
                Pivot(currBaseBrs, currBaseKol);
            }
            if (currBaseKol > KolEff - 2) {
                break;
            }
            double pivotBagi = mtx[currBaseBrs][currBaseKol];
            bagiBrs(currBaseBrs, pivotBagi);
            for (currBrs = currBaseBrs + 1; currBrs < BrsEff; currBrs++) {
                double multiplier = mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            for (currBrs = currBaseBrs - 1; currBrs >= 0; currBrs--) {
                double multiplier = mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            currBaseKol++;

        }

        return mtx;
    }

    public static void writeToFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            if ((BrsEff == 0) && (KolEff == 0)) {
                writer.println("Matriks kosong.");
            }
            for (int i = 0; i < BrsEff; i++) {
                for (int j = 0; j < KolEff; j++) {
                    writer.format("%.3f", mtx[i][j]);
                    writer.print(" ");
                    if (j == (KolEff - 2)) {
                        writer.print("| ");
                    }
                }
                writer.print("\n");
            }
            writer.close();
            System.out.println();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println();
            System.out.println("Tidak dapat menulis ke file eksternal.");
        }
    }

    public static void writeGaussJordanSolution() {
        gaussJordanElimination();

        hapusBarisNol();
        if (noSolusi()) {
            System.out.println();
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        } else if (solusiBanyak()) {
            System.out.println();
            System.out.println("SPL solusi banyak, bentuk parametriknya:");
            String paramChars = "stuvwxyzabcdefghijklmnopqr";
            boolean param1[] = new boolean[KolEff - 1];
            char param2[] = new char[KolEff - 1];

            for (int i = 0; i < param1.length; i++) {
                param1[i] = true;
                param2[i] = '\0';
            }

            for (int i = 0; i < BrsEff; i++) {
                int IdxPertama = getIdxPertama(i);
                if (IdxPertama != -1) {
                    param1[IdxPertama] = false;
                }
            }

            int paramCount = 0;
            for (int i = 0; i < param1.length; i++) {
                if (param1[i] == true) {
                    param2[i] = paramChars.charAt(paramCount);
                    paramCount++;
                }
            }

            for (int i = 0; i < param1.length; i++) {
                String hasil = "X" + (i + 1) + " = ";
                if (!param1[i]) {
                    int BrsIdx = getIdxBarisPertama(i);
                    for (int j = 0; j < KolEff - 1; j++) {
                        if ((mtx[BrsIdx][j] != 0.00) && (mtx[BrsIdx][j] != 1.00)) {
                            hasil += "+ " + (mtx[BrsIdx][j] * -1.00) + String.valueOf(param2[j]) + " ";
                        }
                    }
                } else {
                    hasil += param2[i];
                }
                System.out.println(hasil);
            }

        } else {
            System.out.println();
            System.out.println("SPL solusi unik:");
            for (int i = 0; i < BrsEff; i++) {
                System.out.println("X" + (i + 1) + " = " + mtx[i][KolEff - 1]);
            }
        }
    }

    public static void writeGaussJordanSolutionToFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = "test\\output\\";
            fileName += scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            gaussJordanElimination();
            hapusBarisNol();
            if ((BrsEff == 0) && (KolEff == 0)) {
                writer.println("Matriks kosong.");
            }
            for (int i = 0; i < BrsEff; i++) {
                for (int j = 0; j < KolEff; j++) {
                    writer.format("%.3f", mtx[i][j]);
                    writer.print(" ");
                    if (j == (KolEff - 2)) {
                        writer.print("| ");
                    }
                }
                writer.print("\n");
            }
            if (noSolusi()) {
                writer.println("Sistem persamaan tidak memiliki solusi.");
            } else if (solusiBanyak()) {
                writer.println("SPL solusi banyak, bentuk parametriknya:");

                String paramChars = "stuvwxyzabcdefghijklmnopqr";
                boolean param1[] = new boolean[KolEff - 1];
                char param2[] = new char[KolEff - 1];

                for (int i = 0; i < param1.length; i++) {
                    param1[i] = true;
                    param2[i] = '\0';
                }

                for (int i = 0; i < BrsEff; i++) {
                    int IdxPertama = getIdxPertama(i);
                    if (IdxPertama != -1) {
                        param1[IdxPertama] = false;
                    }
                }

                int paramCount = 0;
                for (int i = 0; i < param1.length; i++) {
                    if (param1[i] == true) {
                        param2[i] = paramChars.charAt(paramCount);
                        paramCount++;
                    }
                }

                for (int i = 0; i < param1.length; i++) {
                    String hasil = "X" + (i + 1) + " = ";
                    if (!param1[i]) {
                        int BrsIdx = getIdxBarisPertama(i);
                        hasil += mtx[BrsIdx][KolEff - 1] + " ";
                        for (int j = 0; j < KolEff - 1; j++) {
                            if ((mtx[BrsIdx][j] != 0.0) && (mtx[BrsIdx][j] != 1.0)) {
                                hasil += "+ " + (mtx[BrsIdx][j] * -1.0) + String.valueOf(param2[j]) + " ";
                            }
                        }
                    } else {
                        hasil += param2[i];
                    }
                    writer.println(hasil);
                }
            } else {
                writer.println("Sistem persamaan memiliki solusi unik:");
                for (int i = 0; i < BrsEff; i++) {
                    writer.println("X" + (i + 1) + " = " + mtx[i][KolEff - 1]);
                }
            }
            writer.close();
            System.out.println();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println();
            System.out.println("Tidak dapat menulis ke file eksternal.");
        }
    }

    public static void ans(double a[][], double b[], int M, int N) {
        BrsEff = M;
        KolEff = N + 1;
        System.out.println("El");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mtx[i][j] = a[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            mtx[i][N] = b[i];
        }

        writeGaussJordanSolution();
        if ((BrsEff == 0) && (KolEff == 0)) {
            System.out.println();
            System.out.println("\nMatriks kosong.");
        }
        System.out.println();
        for (int i = 0; i < BrsEff; i++) {
            for (int j = 0; j < KolEff; j++) {
                System.out.format("%.3f", mtx[i][j]);
                System.out.print(" ");
                if (j == (KolEff - 2)) {
                    System.out.print("| ");
                }
            }
            System.out.print("\n");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("\nApakah ingin menyimpan output ke dalam file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.println("------------------");
        int pil = scan.nextInt();

        if (pil == 1) {
            writeGaussJordanSolutionToFile();
        }

        System.out.println("\nTekan apa saja untuk keluar!");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
