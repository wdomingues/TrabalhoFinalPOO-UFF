package com.company.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Projeto {
    private ArrayList<Edificacao> edificacao;
    private ArrayList<Fornecedor> fornecedores;
    private ArrayList<Funcionario> funcionarios;

    private Map<Insumo, Double> insumosNecessarios = new HashMap<>();

    private int qtdFuncionarios ;

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


    public ArrayList<Edificacao> addEdificacao(Edificacao edi){
        this.edificacao.add(edi);
        return  this.edificacao;
    }
}
