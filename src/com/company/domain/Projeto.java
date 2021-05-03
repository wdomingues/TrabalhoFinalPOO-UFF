package com.company.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Projeto {
    private ArrayList<Edificacao> edificacao;
    private ArrayList<Fornecedor> fornecedores;

    private Map<Insumo, Double> insumosNecessarios = new HashMap<>();

    private int tempoSemanas;
    private Calendar prazo;

    public Projeto() {
        this.edificacao = new ArrayList<Edificacao>();
    }

    public ArrayList<Edificacao> getEdificacao() {
        return edificacao;
    }

    public void setEdificacao(ArrayList<Edificacao> edificacao) {
        this.edificacao = edificacao;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Map<Insumo, Double> getInsumosNecessarios() {
        return insumosNecessarios;
    }

    public void setInsumosNecessarios(Map<Insumo, Double> insumosNecessarios) {
        this.insumosNecessarios = insumosNecessarios;
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

    public ArrayList<Edificacao> addEdificacao(Edificacao edi){
        this.edificacao.add(edi);
        return  this.edificacao;
    }
}
