package com.company.domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Projeto {
    private Edificacao edificacao;
    private ArrayList<Fornecedor> fornecedores;

    private int tempoSemanas;
    private Calendar prazo;

    public Projeto() {
    }

    public Edificacao getEdificacao() {
        return edificacao;
    }

    public void setEdificacao(Edificacao edificacao) {
        this.edificacao = edificacao;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public int getTempoSemanas() {
        return tempoSemanas;
    }

    public void setTempoSemanas(int tempoSemanas) {
        this.tempoSemanas = tempoSemanas;
    }

    public Calendar getPrazo() {
        return prazo;
    }

    public void setPrazo(Calendar prazo) {
        this.prazo = prazo;
    }
}
