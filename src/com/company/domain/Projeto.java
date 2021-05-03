package com.company.domain;

import com.company.helper.SituacaoProjeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Projeto {
    private String nome;
    private Cliente cliente;
    private SituacaoProjeto situacao;
    private ArrayList<Edificacao> edificacao = new ArrayList<>();
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    private ArrayList<Insumo> insumos = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    private Map<String, Double> insumosNecessarios = new HashMap<>();

    private int qtdFuncionarios;

    public Projeto() {
        this.edificacao = new ArrayList<Edificacao>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public SituacaoProjeto getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoProjeto situacao) {
        this.situacao = situacao;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public Map<String, Double> getInsumosNecessarios() {
        return insumosNecessarios;
    }

    public void setInsumosNecessarios(Map<String, Double> insumosNecessarios) {
        this.insumosNecessarios = insumosNecessarios;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Edificacao> addEdificacao(Edificacao edi) {
        this.edificacao.add(edi);
        return this.edificacao;
    }

    public ArrayList<Fornecedor> addFornecedor(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
        return this.fornecedores;
    }

    public ArrayList<Insumo> addInsumo(Insumo insumo){
        this.insumos.add(insumo);
        return this.insumos;
    }

    public ArrayList<Funcionario> addFuncionario(Funcionario funcionario){
        this.funcionarios.add(funcionario);
        return this.funcionarios;
    }
}
