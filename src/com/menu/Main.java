package com.menu;

import java.util.Scanner;
import com.interpolasi.*;
import com.regresi.*;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void clrscr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
            Input.spl("METODE ELIMINASI GAUSS");
        } else if (inp == 2) {
            Input.spl("METODE ELIMINASI GAUSS-JORDAN");
        } else if (inp == 3) {
            Input.spl("METODE MATRIKS BALIKAN");
        } else if (inp == 4) {
            Input.spl("KAIDAH CRAMER");
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

    public static void main(String[] args) {
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
            main(args);
        } else if (inp == 2) {
            determinan();
            main(args);
        } else if (inp == 3) {
            matriksBalikan();
            main(args);
        } else if (inp == 4) {
            Polinom.main();
            main(args);
        } else if (inp == 5) {
            Bicubic.main();
            main(args);
        } else if (inp == 6) {
            LinearBerganda.main();
            main(args);
        } else if (inp != 7) {
            main(args);
        }

    }

}
