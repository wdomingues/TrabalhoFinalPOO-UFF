package com.company.domain;

import java.math.BigDecimal;

public class Fornecedor extends Pessoa implements PessoaJuridica {
    private String insumo;
    private double quantidadeDisponivel;
    private BigDecimal valorUnitario;

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

    public double getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(double quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
