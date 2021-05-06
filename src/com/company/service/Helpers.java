package com.company.service;

import com.company.helper.NumerosOrdinais;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Helpers {

    public static String getOrdinal(int i, boolean isMasculino) {
        NumerosOrdinais[] enumValues = NumerosOrdinais.values();
        if (i < 10)
            return enumValues[i - 1].name() + (isMasculino ? "o" : "a");
        else {
            int pre = i / 10;
            int pos = i % (pre * 10);


            String preStr = enumValues[10 + pre - 2].name() + (isMasculino ? "o" : "a");
            String posStr = (pos == 0 ? "" : " " + enumValues[pos - 1].name() + (isMasculino ? "o" : "a"));
            return preStr + posStr;
        }
    }


    public static int validaInteiroPositivo() {
        String ret;
        int ret2 = 0;
        Scanner scanner = new Scanner(System.in);
        ret = scanner.nextLine();

        try {
            ret2 = Integer.parseInt(ret);
            if (ret2 < 0)
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Inválido.\nDigite um numero para continuar:");
            return validaInteiroPositivo();
        }

        return ret2;
    }

    public static int minimoDiasPorHora(int horas) {
        if (horas < 8)
            return 1;

        int dias = horas / 8;
        return dias;
    }

    public static String monetarioBigDecimal(BigDecimal valor) {
        DecimalFormat decFormat = new DecimalFormat("'R$'0.##\t");
        return decFormat.format(valor);
    }

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
        System.out.println("");
    }

    public static Object[] getSliceOfArray(Object[] arr,
                                           int start, int end) {

        // Get the slice of the Array
        Object[] slice = new Object[end - start];

        // Copy elements of arr to slice
        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }

        // return the slice
        return slice;
    }

}
