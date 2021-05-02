package com.company.domain;

import java.util.ArrayList;

public class Andar {
    private ArrayList<Comodo> comodos;
    private int numeroAndar;
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

    public int getNumeroAndar() {
        return this.numeroAndar;
    }
    public void setNumeroAndar(int numeroAndar) {
        this.numeroAndar = numeroAndar;
    }

    public void addComodo(Comodo comodo) {
        this.comodos.add(comodo);
    }

    public ArrayList<Comodo> getComodos() {
        return comodos;
    }

    public void setComodos(ArrayList<Comodo> comodos) {
        this.comodos = comodos;
    }
}
