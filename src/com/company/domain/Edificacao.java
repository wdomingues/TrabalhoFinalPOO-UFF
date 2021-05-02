package com.company.domain;

import java.util.ArrayList;

public class Edificacao {
    private ArrayList<Andar> andares;
    private int numeroAndares;
    private int prazoDias;

    public Edificacao(int i) {
        this.numeroAndares = i;
    }

    public Edificacao(ArrayList<Andar> andares) {
        this.andares = andares;
    }

    public ArrayList<Andar> getAndares() {
        return andares;
    }

    public void addAndar(Andar andar) {
        this.andares.add(andar);
    }

    public int getNumeroAndares() {
        return numeroAndares;
    }

    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }

    public void setPrazoDias(int prazoDias) {
        this.prazoDias = prazoDias;
    }
}
