import java.util.*;
public class SPL {
    static Scanner scanner = new Scanner(System.in);
    public static void SPLGauss(String suffix) {
        System.out.print("Masukkan banyaknya baris: ");
        int m = scanner.nextInt();
        System.out.print("Masukkan banyaknya variabel x: ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);
        M.BacaMat();
        SPLGauss(M, suffix);
    }

    private static class variable {
        private double[] exp = new double [Solution.varList.length() + 1];
        private static int accVarIndex = 1;
        public String GenerateNewVar () {
            String var = Solution.GetVarName(accVarIndex);
            exp[accVarIndex] = 1;
            accVarIndex++;
            return var;
        }

        public double GetConst () {
            return exp[0];
        }

        public Solution GetVariabel (String var) {
            return GetVariabel(Solution.GetVarIndex(var));
        }

        public Solution GetVariabel (int var) {
            return new Solution(exp[var], var);
        }

        public void SetConst (double val) {
            exp[0] = val;
        }

        public void SetVariabel (String var, double val) {
            SetVariabel(Solution.GetVarIndex(var), val);
        }

        public void SetVariabel (int var, double val) {
            exp[var] = val;
        }

        public String ToString () {
            String out = "";
            for (int i = 1; i < exp.length; i++) {
                if (exp[i] != 0) {
                    if (out == "") {
                        if (exp[i] == 1) {
                            out = Solution.GetVarName(i);
                        } else if (exp[i] == -1) {
                            out = "-" + Solution.GetVarName(i);
                        } else {
                            out = String.format("%.2f", exp[i]) + Solution.GetVarName(i);
                        }
                    } else {
                        if (exp[i] > 0) {
                            if (exp[i] != 1) {
                                out += " + " + String.format("%.2f", exp[i]) + Solution.GetVarName(i);
                            } else {
                                out += " + " + Solution.GetVarName(i);
                            }
                        } else {
                            if (exp[i] != -1) {
                                out += " - " + String.format("%.2f", exp[i] * -1) + Solution.GetVarName(i);
                            } else {
                                out += " - " + Solution.GetVarName(i);
                            }
                        }
                    }
                }
            }
            if (exp[0] != 0) {
                if (out == "") {
                    out = String.format("%.2f", exp[0]);
                } else {
                    if (exp[0] > 0) {
                        out += " + " + String.format("%.2f", exp[0]);
                    } else {
                        out += " - " + String.format("%.2f", exp[0] * -1);
                    }
                }
            } else {
                if (out == "") {
                    out = "0";
                }
            }
            return out;
        }

        public boolean IsEmpty () {
            for (int i = 0; i < exp.length; i++) {
                if (exp[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        public static void ResetVars() {
            accVarIndex = 1;
        }
    }

    public static void SPLGauss(Matriks M, String suffix) {
        boolean noSolusi = false;
        int n = M.GetLastIdxKol(M);
        M = Matriks.EliminasiGauss(M);

        variable[] hasil = new variable[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new variable();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            variable value = new variable();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { 
                            if (idxPertama == -1) {
                                idxPertama = j;
                            } else {
                                value.SetVariabel(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Solution.varList.length(); k++) {
                                value.SetVariabel(k,
                                        value.GetVariabel(k).val - (hasil[j].GetVariabel(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    noSolusi = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }
        if (noSolusi) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        variable.ResetVars();
        }
    }

    public static void SPLGaussJordan(String suffix) {
        System.out.print("Masukkan banyaknya baris: ");
        int m = scanner.nextInt();
        System.out.print("Masukkan banyaknya variabel x: ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);
        M.BacaMat();
        SPLGaussJordan(M, suffix);
    }

    public static void SPLGaussJordan(Matriks M, String suffix) {
        boolean noSolusi = false;
        int m = M.Baris;
        int n = M.Kolom - 1;
        M = Matriks.EliminasiGaussJordan(M);
        variable[] hasil = new variable[n];

        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new variable();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            variable value = new variable();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) {
                            if (idxPertama == -1) {
                                idxPertama = j;
                            } else {
                                value.SetVariabel(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else {
                            for (int k = 0; k < Solution.varList.length(); k++) {
                                value.SetVariabel(k,
                                        value.GetVariabel(k).val - (hasil[j].GetVariabel(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    noSolusi = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }
        if (noSolusi) {
            System.out.println("SPL tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        }
        variable.ResetVars();
        }
}
