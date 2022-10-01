import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// EliminasiGaussJordan Class
public class EliminasiGaussJordan {
    private double[][] mtx = new double[100][100];
    private int BrsEff;
    private int KolEff;

    public EliminasiGaussJordan() {
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mtx[i][j] = 0;
            }
        }
        this.BrsEff = 0;
        this.KolEff = 0;
    }

    public EliminasiGaussJordan(int newBrsEff, int newKolEff) {
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mtx[i][j] = 0;
            }
        }
        this.BrsEff = newBrsEff;
        this.KolEff = newKolEff;
    }


    public EliminasiGaussJordan(EliminasiGaussJordan M) {
        this.BrsEff = M.BrsEff;
        this.KolEff = M.KolEff;

        for(int i = 0; i < this.BrsEff; i++){
            for(int j = 0; j < this.KolEff; j++) {
                this.mtx[i][j] = M.mtx[i][j];
            }
        }

    }

    public void setBrsEff(int n) {
        this.BrsEff = n;
    }

    public void setKolEff(int n) {
        this.KolEff = n;
    }

    public int getBrsEff() {
        return this.BrsEff;
    }

    public int getKolEff() {
        return this.KolEff;
    }

    private double power(double X, int power) {
        double hasil = 1.0;
        for(int i = 0; i < power; i ++) {
            hasil *= X;
        }
        return hasil;
    }

    private void clear() {
        this.BrsEff = 0;
        this.KolEff = 0;
    }

    private int getMaxAbsKolIdx(int currBrs, int currKol){
        int maxBrsIdx = currBrs;
        for (int i = currBrs; i < this.BrsEff; i ++) {
            if (Math.abs(this.mtx[i][currKol]) > Math.abs(this.mtx[maxBrsIdx][currKol])) {
                maxBrsIdx = i;
            }
        }
        return maxBrsIdx;
    }

    private void swapBrs(int Brs1Idx, int Brs2Idx) {
        double[] tempBrs = new double[this.KolEff];
        for (int j = 0; j < this.KolEff; j++) {
            tempBrs[j] = this.mtx[Brs1Idx][j];
        }
        for (int j = 0; j < this.KolEff; j++) {
            this.mtx[Brs1Idx][j] = this.mtx[Brs2Idx][j];
        }
        for (int j = 0; j < this.KolEff; j++) {
            this.mtx[Brs2Idx][j] = tempBrs[j];
        }

    }

    private void Pivot(int currBrs, int currKol) {
        int pivotBrs = this.getMaxAbsKolIdx(currBrs, currKol);
        this.swapBrs(currBrs, pivotBrs);
    }

    private void bagiBrs(int i, double Bagi) {
        for(int j = 0; j < this.KolEff; j++) {
            this.mtx[i][j] /= Bagi;
        }
    }

    private void kurangBrs(int i, int j, double Kali) {
        for(int k = 0; k < this.KolEff; k++) {
            this.mtx[i][k] -= (this.mtx[j][k] * Kali);
        }
    }

    private boolean allBarisNol(int currBrs) {
        for(int i = 0; i < this.KolEff; i++) {
            if (this.mtx[currBrs][i] != 0) {
                return false;
            }
        }
        return true;

    }

    private boolean isBarisNol() {
        for(int i = 0; i < this.BrsEff; i++) {
            if (this.allBarisNol(i) == true) {
                return true;
            }
        }
        return false;

    }

    private boolean isUnik(int currBrs) {
        for(int i = 0; i < this.KolEff - 1; i++) {
            if (this.mtx[currBrs][i] != 0) {
                return false;
            }
        }

        if (this.mtx[currBrs][this.KolEff - 1] == 0) {
            return false;
        }

        return true;

    }

    private boolean BarisUnik() {
        for(int i = 0; i < this.BrsEff; i++) {
            if (this.isUnik(i) == true) {
                return true;
            }
        }
        return false;

    }

    private boolean noSolusi() {
        return (this.BarisUnik());
    }

    private boolean solusiBanyak() {
        return (this.BrsEff < (this.KolEff - 1));

    }

    private int getIdxPertama(int currBrs) {
        boolean isNol = true;
        for (int i = 0; i < this.KolEff - 1; i ++) {
            if (this.mtx[currBrs][i] == 1.0) {
                return i;
            }
        }

        return -1;

    }

    private int getIdxBarisPertama(int IdxPertama) {
        for (int i = 0; i < this.BrsEff; i ++) {
            if (this.getIdxPertama(i) == IdxPertama) {
                return i;
            }
        }
        return -1;
    }

    private void hapusBaris(int currBrs) {
        for (int i = currBrs; i < this.BrsEff - 1; i ++) {
            for (int j = 0; j < this.KolEff; j ++) {
                this.mtx[i][j] = this.mtx[i + 1][j];
            }
        }
        this.BrsEff --;
    }

    private void hapusBarisNol() {
        for (int i = this.BrsEff - 1; i >= 0; i --) {
            if (allBarisNol(i)) {
                hapusBaris(i);
            }
        }
    }

    private void gaussJordanElimination() {
        int currBaseBrs = 0;
        int currBaseKol = 0;
        int currBrs;
        for (; (currBaseBrs < this.BrsEff) && (currBaseKol < this.KolEff - 1); currBaseBrs ++) {
            this.Pivot(currBaseBrs, currBaseKol);
            while ((this.mtx[currBaseBrs][currBaseKol] == 0) && (currBaseKol < this.KolEff - 1)) {
                currBaseKol ++;
                this.Pivot(currBaseBrs, currBaseKol);
            }
            if (currBaseKol > this.KolEff - 2) {
                break;
            }
            double pivotBagi = this.mtx[currBaseBrs][currBaseKol];
            this.bagiBrs(currBaseBrs, pivotBagi);
            for (currBrs = currBaseBrs + 1; currBrs < this.BrsEff; currBrs ++) {
                double multiplier = this.mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            for (currBrs = currBaseBrs - 1; currBrs >= 0; currBrs --) {
                double multiplier = this.mtx[currBrs][currBaseKol];
                kurangBrs(currBrs, currBaseBrs, multiplier);
            }
            currBaseKol ++;

        }
    }

    public void read() {
        Scanner scan = new Scanner(System.in);

        //Membaca baris
        System.out.println();
        System.out.print("Masukan jumlah baris: ");
        this.BrsEff = scan.nextInt();

        //Membaca kolom
        System.out.print("Masukan jumlah kolom: ");
        this.KolEff = scan.nextInt();

        System.out.println();
        System.out.println("Input matriks: ");
        for (int i = 0; i < this.BrsEff; i++) {
            for (int j = 0; j < this.KolEff; j++) {
                this.mtx[i][j] = scan.nextDouble();
            }
        }
    }

    public void readFromFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.next().equals("BARIS")) {
                this.BrsEff = reader.nextInt();
            } else {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                return;
            }
            if (reader.next().equals("KOLOM")) {
                this.KolEff = reader.nextInt();
            } else {
                  System.out.println();
                  System.out.println("Format file eksternal salah.");
                  return;
            }

            try {
                for (int Brs = 0; Brs < this.BrsEff; Brs++) {
                       for (int Kol = 0; Kol < this.KolEff; Kol++) {
                           this.mtx[Brs][Kol] = reader.nextDouble() ;
                      }
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                this.clear();
                return;
            }
            reader.close();
            System.out.println();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");
        } catch (Exception i) {
            System.out.println();
            System.out.println("Tidak dapat membaca dari file eksternal.");
        }
    }

    public void readForHilbert() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Masukan dimensi matriks Hilbert : ");
        this.BrsEff = scan.nextInt();
        this.KolEff = this.BrsEff + 1;

        for (int i = 0; i < this.BrsEff; i ++) {
            for (int j = 0; j < this.KolEff - 1; j ++) {
                this.mtx[i][j] = 1.0 / (i + j + 1.0);
            }
        }
        for (int i = 0; i < this.BrsEff; i ++) {
            this.mtx[i][this.KolEff - 1] = 1.0;
        }
    }

    public void readForHilbertFromFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.next().equals("DIMENSI")) {
                this.BrsEff = reader.nextInt();
                this.KolEff = this.BrsEff + 1;
            } else {
                System.out.println();
                System.out.println("Format file eksternal salah.");
                return;
            }

            for (int i = 0; i < this.BrsEff; i ++) {
                for (int j = 0; j < this.KolEff - 1; j ++) {
                    this.mtx[i][j] = 1.0 / (i + j + 1.0);
                }
            }

            for (int i = 0; i < this.BrsEff; i ++) {
                this.mtx[i][this.KolEff - 1] = 1.0;
            }
            reader.close();
            System.out.println();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");
        } catch (Exception i) {
            System.out.println();
            System.out.println("Tidak dapat membaca dari file eksternal.");
        }
    }


    public void write() {
        if ((this.BrsEff == 0) && (this.KolEff == 0)) {
            System.out.println();
            System.out.println("Matriks kosong.");
        }
        System.out.println();
        for (int i = 0; i < this.BrsEff; i++) {
            for (int j = 0; j < this.KolEff; j++) {
                System.out.format("%.3f", this.mtx[i][j]);
                System.out.print(" ");
                if (j == (this.KolEff -2)) {
                    System.out.print("| ");
                }
            }
            System.out.print("\n");
        }
    }


    public void writeToFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            if ((this.BrsEff == 0) && (this.KolEff == 0)) {
                writer.println("Matriks kosong.");
            }
            for (int i = 0; i < this.BrsEff; i++) {
                for (int j = 0; j < this.KolEff; j++) {
                    writer.format("%.3f",this.mtx[i][j]);
                    writer.print(" ");
                    if (j == (this.KolEff -2)) {
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

    public void writeGaussJordan() {
        EliminasiGaussJordan KurangMat = new EliminasiGaussJordan(this);
        KurangMat.gaussJordanElimination();
        KurangMat.write();
    }

    public void writeGaussJordanToFile() {
        EliminasiGaussJordan KurangMat = new EliminasiGaussJordan(this);
        KurangMat.gaussJordanElimination();
        KurangMat.writeToFile();
    }

    public void writeGaussJordanSolution() {
        EliminasiGaussJordan KurangMat = new EliminasiGaussJordan(this);
        KurangMat.gaussJordanElimination();
        KurangMat.hapusBarisNol();
        if (KurangMat.noSolusi()) {
            System.out.println();
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        } else if (KurangMat.solusiBanyak()) {
            System.out.println();
            System.out.println("SPL solusi banyak, bentuk parametriknya:");
            String paramChars = "stuvwxyzabcdefghijklmnopqr";
            boolean param1[] = new boolean[KurangMat.KolEff - 1];
            char param2[] = new char[KurangMat.KolEff - 1];

            for (int i = 0; i < param1.length; i ++) {
                param1[i] = true;
                param2[i] = '\0';
            }

            for (int i = 0; i < KurangMat.BrsEff; i ++) {
                int IdxPertama = KurangMat.getIdxPertama(i);
                if (IdxPertama != -1) {
                    param1[IdxPertama] = false;
                }
            }

            int paramCount = 0;
            for (int i = 0; i < param1.length; i ++) {
                if (param1[i] == true) {
                    param2[i] = paramChars.charAt(paramCount);
                    paramCount ++;
                }
            }

            for (int i = 0; i < param1.length; i ++) {
                String hasil = "X" + (i + 1) + " = ";
                if (!param1[i]) {
                    int BrsIdx = KurangMat.getIdxBarisPertama(i);
                    hasil += KurangMat.mtx[BrsIdx][KurangMat.KolEff - 1] + " ";
                    for (int j = 0; j < KurangMat.KolEff - 1; j ++) {
                        if ((KurangMat.mtx[BrsIdx][j] != 0.00) && (KurangMat.mtx[BrsIdx][j] != 1.00)) {
                            hasil += "+ " + (KurangMat.mtx[BrsIdx][j] * -1.00) + String.valueOf(param2[j]) + " ";
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
            for (int i = 0; i < KurangMat.BrsEff; i ++) {
                System.out.println("X" + (i + 1) + " = " + KurangMat.mtx[i][KurangMat.KolEff - 1]);
            }
        }
    }

    public void writeGaussJordanSolutionToFile() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan nama file eksternal: ");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            EliminasiGaussJordan KurangMat = new EliminasiGaussJordan(this);
            KurangMat.gaussJordanElimination();
            KurangMat.hapusBarisNol();

            if (KurangMat.noSolusi()) {
                writer.println("Sistem persamaan tidak memiliki solusi.");
            } else if (KurangMat.solusiBanyak()) {
                writer.println("SPL solusi banyak, bentuk parametriknya:");

                String paramChars = "stuvwxyzabcdefghijklmnopqr";
                boolean param1[] = new boolean[KurangMat.KolEff - 1];
                char param2[] = new char[KurangMat.KolEff - 1];

                for (int i = 0; i < param1.length; i ++) {
                    param1[i] = true;
                    param2[i] = '\0';
                }

                for (int i = 0; i < KurangMat.BrsEff; i ++) {
                    int IdxPertama = KurangMat.getIdxPertama(i);
                    if (IdxPertama != -1) {
                        param1[IdxPertama] = false;
                    }
                }

                int paramCount = 0;
                for (int i = 0; i < param1.length; i ++) {
                    if (param1[i] == true) {
                        param2[i] = paramChars.charAt(paramCount);
                        paramCount ++;
                    }
                }

                for (int i = 0; i < param1.length; i ++) {
                    String hasil = "X" + (i + 1) + " = ";
                    if (!param1[i]) {
                        int BrsIdx = KurangMat.getIdxBarisPertama(i);
                        hasil += KurangMat.mtx[BrsIdx][KurangMat.KolEff - 1] + " ";
                        for (int j = 0; j < KurangMat.KolEff - 1; j ++) {
                            if ((KurangMat.mtx[BrsIdx][j] != 0.0) && (KurangMat.mtx[BrsIdx][j] != 1.0)) {
                                hasil += "+ " +(KurangMat.mtx[BrsIdx][j] * -1.0) + String.valueOf(param2[j]) + " ";
                            }
                        }
                    } else {
                        hasil += param2[i];
                    }
                    writer.println(hasil);
                }
            } else {
                writer.println("Sistem persamaan memiliki solusi unik:");
                for (int i = 0; i < KurangMat.BrsEff; i ++) {
                    writer.println("X" + (i + 1) + " = " + KurangMat.mtx[i][KurangMat.KolEff - 1]);
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

    private final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }


    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        boolean EndMenu = false;
        boolean EndCase;

        while (!EndMenu) {

            clearConsole();

            System.out.println("||========================== Metode Eliminasi GAUSS-JORDAN ===========================||");
            System.out.println();
            System.out.println(">> Pilih menu  :");
            System.out.println("1. Matriks Augmented");
            System.out.println("2. Matriks Hilbert");
            System.out.println("3. Exit");
            System.out.print(">> ");


            String OptionMenu = scan.nextLine();
            switch (OptionMenu) {
                case "1" :

                    EliminasiGaussJordan M1 = new EliminasiGaussJordan();
                    EndCase = false;
                    while (!EndCase) {

                        System.out.println();
                        System.out.println("||==================================== MATRIKS AUGMENTED =========================================||");
                        System.out.println();
                        System.out.println(">> Pilih menu  :");
                        System.out.println("1. Input dari keyboard");
                        System.out.println("2. Input dari file eksternal");
                        System.out.println("3. Tampilkan solusi SPL");
                        System.out.println("4. Back");
                        System.out.print(">> ");

                        String OptionCase = scan.nextLine();
                        switch (OptionCase) {
                            case "1" :
                                M1.read();
                                break;

                            case "2" :
                                M1.readFromFile();
                                break;

                            case "3" :
                                if (M1.getBrsEff() == 0) {
                                    System.out.println();
                                    System.out.println("Matriks kosong.");
                                } else {
                                    System.out.println();
                                    System.out.print("Apakah anda ingin mengoutput hasilnya ke file eksternal? (Y, T): ");
                                    String Input = scan.nextLine();
                                    if ("Yy".contains(Input)) {
                                        M1.writeGaussJordanSolutionToFile();
                                    } else {
                                        M1.writeGaussJordanSolution();
                                    }
                                }
                                break;

                            case "4" :
                                EndCase = true;
                                break;

                            default :
                                System.out.println();
                                System.out.println("Input salah, masukan input ulang.");
                                break;
                        }
                    }
                    break;

                case "2" :

                    EliminasiGaussJordan M2 = new EliminasiGaussJordan();
                    EndCase = false;
                    while (!EndCase) {

                        System.out.println();
                        System.out.println("||===================================== MATRIKS HILBERT ==========================================||");
                        System.out.println();
                        System.out.println(">> Pilih menu  :");
                        System.out.println("1. Input matriks Hilbert dari keyboard");
                        System.out.println("2. Input matriks Hilbert dari file eksternal");
                        System.out.println("3. Tampilkan solusi matriks Hilbert");
                        System.out.println("4. Back");
                        System.out.print(">> ");

                        String OptionCase = scan.nextLine();
                        switch (OptionCase) {
                            case "1" :
                                M2.readForHilbert();
                                break;

                            case "2" :
                                M2.readForHilbertFromFile();
                                break;

                            case "3" :
                                if (M2.getBrsEff() == 0) {
                                    System.out.println();
                                    System.out.println("matriks Hilbert kosong.");
                                } else {
                                    System.out.println();
                                    System.out.print("Apakah anda ingin mengoutput hasilnya ke file eksternal? (Y, T): ");
                                    String Input = scan.nextLine();
                                    if ("Yy".contains(Input)) {
                                        M2.writeGaussJordanSolutionToFile();
                                    } else {
                                        M2.writeGaussJordanSolution();
                                    }
                                }
                                break;

                            case "4" :
                                EndCase = true;
                                break;

                            default :
                                System.out.println();
                                System.out.println("Input salah, masukan input ulang.");
                                break;
                        }

                    }
                    break;
                case "3" :
                    EndMenu = true;
                    break;

                default :
                    System.out.println();
                    System.out.println("Input salah, masukan input ulang.");
                    break;
            }
        }
    }
}
