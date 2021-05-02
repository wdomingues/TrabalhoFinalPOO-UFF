package com.company.domain;

import java.util.ArrayList;

public class Edificacao {
    private ArrayList<Andar> andares;
    private int numeroAndares;
    private String localidade;

    public Edificacao(int i) {
    }

    public Edificacao(ArrayList<Andar> andares) {
        this.andares = andares;
    }

    public ArrayList<Andar> getAndares() {
        return andares;
    }

    public void setAndares(int quantidadeAndares) {
        this.numeroAndares = quantidadeAndares;
    }

    public int getNumeroAndares() {
        return numeroAndares;
    }

    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
