import java.util.*;
import java.io.*;

public class Matriks {
    Scanner scanner = new Scanner(System.in);
    public static final double decPoint = 10000000000d;
    public int KolMin = 0;
    public int BrsMin = 0;
    public int KolMax = 100;
    public int BrsMax = 100;
    public int Baris; 
    public int Kolom; 
    public double[][] Mat;
    public Matriks(int baris, int kolom) {
        this.Baris = baris;
        this.Kolom = kolom;
        this.Mat = new double[baris][kolom];
    }

    public Matriks(double[][] Mat) {
        this.Baris = Mat.length;
        this.Kolom = Mat[0].length;
        this.Mat = new double[Mat.length][Mat[0].length];
        for (int i = 0; i < Mat.length; i++)
            for (int j = 0; j < Mat[0].length; j++)
                this.Mat[i][j] = Mat[i][j];
    }

    public Matriks(String file_name) throws FileNotFoundException {
        ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
        File file = new File(file_name);
        Scanner input = new Scanner(file);
        int Baris = -1;
        int Kolom = -1;
        while (input.hasNextLine()) {
            Baris++;
            Mat.add(new ArrayList<Double>());
            String baris = input.nextLine();
            Scanner scanBaris = new Scanner(baris);
            while (scanBaris.hasNextDouble()) {
                Double element = scanBaris.nextDouble();
                Mat.get(Baris).add(element);
            }
        }

        if (Baris == 0) {
            System.out.println("Tidak dapat membaca file");
        } else {
            Kolom = Mat.get(0).size();
            this.Mat = new double[Mat.size()][Mat.get(0).size()];
            for (int i = BrsMin; i <= Baris; i++) {
                for (int j = KolMin; j < Kolom; j++) {
                    this.Mat[i][j] = Mat.get(i).get(j);
                }
            }
            this.Baris = Baris + 1;
            this.Kolom = Kolom;
        }
    }


    public int GetFirstIdxBrs(Matriks M) {
        return BrsMin;
    }

    public int GetFirstIdxKol(Matriks M) {
        return KolMin;
    }

    public int GetLastIdxBrs(Matriks M) {
        return M.Baris - 1;
    }

    public int GetLastIdxKol(Matriks M) {
        return M.Kolom - 1;
    }

    public int NbElmt(Matriks M) {
        return (M.Baris * M.Kolom);
    }

    public static Matriks Identitas(int N) {
        Matriks I = new Matriks(N, N);
        for (int i = 0; i < N; i++)
            I.Mat[i][i] = 1;
        return I;
    }

    public static Matriks Hilbert(int N) {
        Matriks H = new Matriks(N, N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                H.Mat[i][j] = 1.0 / (i + j + 1);
            }
        }
        return H;
    }

    public void BacaMat() {
        System.out.println("Silahkan masukan Matriks : ");
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Mat[i][j] = scanner.nextDouble();
            }
        }
        System.out.print("\n");
    }


    public void TulisMat() {
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Mat[i][j] += 0;
                System.out.printf("%.2f ", this.Mat[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static Matriks Kali(Matriks M, double k) {
        Matriks out = new Matriks(M.Baris, M.Kolom);
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                out.Mat[i][j] = M.Mat[i][j] * k;
            }
        }
        return out;
    }

    public void Kali(double k) {
        this.Mat = Kali(this, k).Mat;
    }

    public static Matriks Kali(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.Baris, N.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                out.Mat[i][j] = 0;
                for (int k = 0; k < M.Kolom; k++) {
                    out.Mat[i][j] += M.Mat[i][k] * N.Mat[k][j];
                }
            }
        }

        return out;
    }

    public void Swap(int Brs1, int Brs2) {
        double[] temp = Mat[Brs1];
        Mat[Brs1] = Mat[Brs2];
        Mat[Brs2] = temp;
    }

    public void KaliBaris(int Baris, double x) {
        for (int i = 0; i < Kolom; i++) {
            Mat[Baris][i] *= x;
        }
    }

    public void PlusBaris(int Baris1, int Baris2, double k) {
        for (int i = 0; i < Kolom; i++) {
            Mat[Baris1][i] += Mat[Baris2][i] * k;
        }
    }

    public void PlusBaris(int Baris1, int Baris2) {
        PlusBaris(Baris1, Baris2, 1);
    }

    public void MinusBaris(int a, int b, double k) {
        PlusBaris(a, b, -k);
    }

    public void MinusBaris(int a, int b) {
        MinusBaris(a, b, 1);
    }

    public static boolean IsIdentitas(Matriks M) {
        boolean out = true;
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                if (!(((i == j) && M.Mat[i][j] == 1) || ((i != j) && M.Mat[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }


    public static Matriks EliminasiGauss(Matriks in) {
        Matriks M = new Matriks(1, 1);
        M = Copy(in);
        int[] nolCount = new int[M.Baris];
        for (int i = 0; i < M.Baris; i++) {
            nolCount[i] = 0;
            int j = 0;
            while (j < M.Kolom && M.Mat[i][j] == 0) {
                nolCount[i]++;
                j++;
            }
        }
        // sorting
        for (int i = 0; i < M.Baris; i++) { 
            for (int j = 0; j < M.Baris - 1; j++) {
                if (nolCount[j] > nolCount[j + 1]) {
                    int temp;
                    M.Swap(j, j + 1);
                    temp = nolCount[j];
                    nolCount[j] = nolCount[j + 1];
                    nolCount[j + 1] = temp;
                }
            }
        }
        // Reduksi baris
        int iden = 0;

        for (int i = 0; i < M.Baris; i++) {
            while (i + iden < M.Kolom && M.Mat[i][i + iden] == 0) {
                iden++;
            }
            if (i + iden < M.Kolom) {
                // Membuat 1 utama
                M.KaliBaris(i, 1 / M.Mat[i][i + iden]);
                // Melakukan pengurangan baris
                for (int j = i + 1; j < M.Baris; j++) {
                    if (M.Mat[j][i + iden] != 0) {
                        M.KaliBaris(j, 1 / M.Mat[j][i + iden]);
                        M.MinusBaris(j, i);
                    }
                }
            }
        }
        M.Approximate();
        return M;
    }

    public static Matriks EliminasiGaussJordan(Matriks in) {
        Matriks M = EliminasiGauss(in);
        int iden = 0;
        for (int i = 0; i < M.Baris; i++) {
            while (i + iden < M.Kolom && M.Mat[i][i + iden] == 0) {
                iden++;
            }
            if (i + iden < M.Kolom) {
                for (int j = i - 1; j >= 0; j--) {
                    if (M.Mat[j][i + iden] != 0) {
                        M.MinusBaris(j, i, M.Mat[j][i + iden]);
                    }
                }
            }
        }
        M.Approximate();
        return M;
    }

    public static void Copy(Matriks from, Matriks to) {
        to.Baris = from.Baris;
        to.Kolom = from.Kolom;
        to.Mat = new double[from.Baris][from.Kolom];

        for (int i = 0; i < to.Baris; i++) {
            for (int j = 0; j < to.Kolom; j++) {
                to.Mat[i][j] = from.Mat[i][j];
            }
        }
    }

    public static Matriks Copy(Matriks from) {
        Matriks to = new Matriks(1, 1);
        Copy(from, to);
        return to;
    }

    private static double Approximate(double x) {
        return (Math.round(x * decPoint) / decPoint);
    }

    private void Approximate() {
        for (int i = 0; i < Baris; i++) {
            for (int j = 0; j < Kolom; j++) {
                Mat[i][j] = Approximate(Mat[i][j]);
            }
        }
    }

    public static void TulisFile(String file, Matriks M) {
        try {
            File F = new File(file);
            if (!(F.exists())) {
                F.createNewFile();
            }

            FileWriter FF = new FileWriter(file);
            PrintWriter print = new PrintWriter(FF);

            for (int i = 0; i < M.Baris; i++) {
                for (int j = 0; j < M.Kolom; j++) {
                    M.Mat[i][j] += 0;
                    print.printf("%.2f ", M.Mat[i][j]);
                }
                print.printf("\n");
            }

            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void h() {
        this.Mat[BrsMin][GetLastIdxKol(this)] = 1;
    }
}
