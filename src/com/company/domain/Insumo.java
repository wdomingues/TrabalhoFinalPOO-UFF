package com.company.domain;


import java.util.ArrayList;

public class Insumo {
    private String nome;
    private ArrayList<Fornecedor> fornecedores;

    public Insumo() {
        this.fornecedores = new ArrayList<Fornecedor>();
    }

    public Insumo(String nome){
        this.nome = nome;
        this.fornecedores = new ArrayList<Fornecedor>();
    }

    public Insumo(String nome, ArrayList<Fornecedor> fornecedores) {
        this.nome = nome;
        this.fornecedores = fornecedores;
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


    public double quantidadeTotal(){
        double qtd =0;
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

    public void removeFornecedor(Fornecedor fornecedor) {
        this.fornecedores.removeIf(f -> f.getCnpj().equals(fornecedor.getCnpj()));
    }
}
