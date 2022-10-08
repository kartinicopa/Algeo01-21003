package com.determinan;

import com.matriksgeneral.Kofaktor;

public class EkspansiKofaktor {
    public static float ans(float matriks[][], int ord) {
        float det;

        if (ord==1){
            return matriks[0][0];
        }else{
            det = 0;
            for (int i=0; i<ord; i++){
                det += matriks[0][i]*Kofaktor.kofaktor(matriks, 0, i, ord);
            }
            return det;                                                                                                                                                                                                                                                                  
        }

    }
}