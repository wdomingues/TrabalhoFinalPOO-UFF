package com.company.domain;

import java.util.ArrayList;

public class Edificacao  implements Cloneable {
    private int ordem;
    private ArrayList<Andar> andares;
    private int numeroAndares;
    private int prazoDias;

    public Edificacao(int i) {
        this.numeroAndares = i;
        this.andares = new ArrayList<Andar>();
    }

    public Edificacao(ArrayList<Andar> andares) {
        this.andares = andares;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public void setAndares(ArrayList<Andar> andares) {
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

    public Object cloneEdificacao() throws CloneNotSupportedException {
        return super.clone();
    }
}
