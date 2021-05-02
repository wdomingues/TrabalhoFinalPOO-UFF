package com.company.domain;


import java.util.ArrayList;

public class Insumo {
    private String nome;
    private ArrayList<Fornecedor> fornecedores;

    public Insumo() {
        this.fornecedores = new ArrayList<Fornecedor>();
    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public int quantidadeTotal(){
        int qtd =0;
        for (Fornecedor f:
             fornecedores) {
            qtd = qtd + f.getQuantidadeDisponivel();

        }
        return qtd;
    }

    public ArrayList<Fornecedor> addFornecedor(Fornecedor fornecedor){
        this.fornecedores.add(fornecedor);
        return this.fornecedores;
    }
}
