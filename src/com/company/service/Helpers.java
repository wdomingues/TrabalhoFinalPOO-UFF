package com.company.service;

import com.company.helper.Enum_ordinais;

public class Helpers {

    public static String getOrdinal(int i) {
        Enum_ordinais[] enumValues = Enum_ordinais.values();
        if (i < 10)
            return enumValues[i - 1].name();
        else {
            int pre = i / 10;
            int pos = i % (pre * 10);


            return enumValues[10 + pre - 2].name() + " " + enumValues[pos - 1].name();
        }
    }
}
