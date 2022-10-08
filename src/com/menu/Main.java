package com.menu;

import java.util.Scanner;
import com.interpolasi.*;
import com.regresi.*;
import com.spl.SPL;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Inputspl(String x) {
        clrscr();
        int N;
        System.out.println("JENIS INPUT BENTUK SPL");
        System.out.println();
        System.out.println("1. Ax = b");
        System.out.println("2. Augmented");
        System.out.println("3. Persamaan");
        System.out.println("4. File (Matriks Augmented");
        System.out.println("5. Keluar");
        System.out.println("----------------------------");
        System.out.print("Input : ");
        N = in.nextInt();

        clrscr();
        if (N == 1) {
            Input.spl(x, 1);
        } else if (N == 2) {
            Input.spl(x, 2);
        } else if (N == 3) {
            Input.spl(x, 3);
        } else if (N == 4) {
            Input.olahFile(x);
        }

        else if (N != 5) {
            Inputspl(x);
        }
    }

    public static void splMenu() {
        System.out.println("SISTEM PERSAMAAN LINEAR\n");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Keluar");
        System.out.println("----------------------------");
        System.out.print("Input : ");

        int inp = in.nextInt();

        clrscr();
        if (inp == 1) {
            Inputspl("METODE ELIMINASI GAUSS");
        } else if (inp == 2) {
            Inputspl("METODE ELIMINASI GAUSS-JORDAN");
        } else if (inp == 3) {
            Inputspl("METODE MATRIKS BALIKAN");
        } else if (inp == 4) {
            Inputspl("KAIDAH CRAMER");
        } else if (inp != 5) {
            splMenu();
        }
    }

    public static void determinan() {
        clrscr();
        System.out.println("DETERMINAN\n");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");
        System.out.println("3. Keluar");
        System.out.println("----------------------------");
        System.out.print("Input : ");

        int inp = in.nextInt();

        clrscr();
        if (inp == 1) {
            Input.matriks("METODE REDUKSI BARIS");
        } else if (inp == 2) {
            Input.matriks("EKSPANSI KOFAKTOR");
        } else if (inp != 3) {
            determinan();
        }
    }

    public static void matriksBalikan() {

    }

    public static void utama() {
        clrscr();
        System.out.println("MENU\n");
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
        System.out.println("----------------------------");
        System.out.print("Input : ");

        int inp = in.nextInt();

        clrscr();
        if (inp == 1) {
            splMenu();
            utama();
        } else if (inp == 2) {
            determinan();
            utama();
        } else if (inp == 3) {
            matriksBalikan();
            utama();
        } else if (inp == 4) {
            Polinom.main();
            utama();
        } else if (inp == 5) {
            Bicubic.main();
            utama();
        } else if (inp == 6) {
            Inputspl("REGRESI");
            utama();
        } else if (inp != 7) {
            utama();
        } else if (inp == 7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        utama();
    }

}
