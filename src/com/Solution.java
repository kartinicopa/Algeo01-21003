import java.util.*;
import java.io.*;
public class Solution {
    public static final String varList = "abcdefghijklmnopqrstuvwxyz";

    public double val;
    public int var;
    public String varName;

    public Solution (double val, int var) {
        this.val = val;
        this.var = var;
        this.varName = GetVarName(var);
    }

    public static String GetVarName (int var) {
        if (var == 0) {
            return "";
        } else {
            return varList.substring(var - 1, var);
        }
    }

    public static int GetVarIndex (String var) {
        return varList.indexOf(var) + 1;
    }

    /*public static void interpolasiSolver() {
        Scanner scanner = new Scanner(System.in);
        int type = Utils.inputData();
        // Inisialisasi Interpolasi
        Interpolation interpolasi = new Interpolation();
        matriks titik = new matriks();
        if (type == 1) {
            titik = interpolasi.readTitikInterpolation();
        } else if (type == 2) {
            titik = interpolasi.readFileTitikInterpolation(Utils.inputDariFile());
        }

        // Input titik interpolasi dan ubah ke Matriks Augmented
        interpolasi.convertToMatAug(titik);
        matriks copyMat = new matriks();
        copyMat.copyMatriksToThis(titik);
        titik.eliminasiGauss();
        double[] test = interpolasi.getGauss(titik);

        System.out.println("Persamaan Interpolasi yang terbentuk: ");
        interpolasi.displayPolinomInterpolasi(test);
        System.out.println();

        // Input x taksiran
        int i, count;
        System.out.print("Masukkan banyaknya Solution x yang ingin ditaksir: ");
        count = scanner.nextInt();
        double[] y = new double[count];
        double x;
        double[] bilX = new double[count];

        System.out.println("Masukkan Solution x yang akan ditaksir: ");
        for (i = 0; i < count; i++) {
            x = interpolasi.readxTaksiran();
            bilX[i] = x;
            y[i] = interpolasi.interpolasiPolinom(test, x);
        }

        System.out.println();
        interpolasi.displayInterpolasi(count, bilX, y);
        System.out.println();
        System.out.print("Simpan Hasil Interpolasi?(y/n) ");
        char simpan = scanner.next().charAt(0);
        String namaFile;

        if (simpan == 'y') {
            System.out.print("Masukkan nama file untuk disimpan <namafile.txt>: ");
            namaFile = scanner.next();
            interpolasi.displayInterpolasiToFile(copyMat, test, count, bilX, y, namaFile);
        }
    } */
}