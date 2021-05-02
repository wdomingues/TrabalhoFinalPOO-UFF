package com.company.domain;

import java.util.ArrayList;

public class Andar {
    private ArrayList<Comodo> comodos;
    private int colunas;

    public Andar(int colunas) {
        this.colunas = colunas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }
}
