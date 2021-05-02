package com.company.domain;

public class Fornecedor extends Pessoa implements PessoaJuridica {
    private String insumo;
    private int quantidadeDisponivel;
    private double valorUnitario;

    public Fornecedor(String nome, String documento) {
        super(nome, documento);
    }

    @Override
    public String getCnpj() {
        return this.documento;
    }

    @Override
    public String getRazaoSocial() {
        return this.nome;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
