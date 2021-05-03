package com.company.domain;

import java.math.BigDecimal;

public class Fornecedor extends Pessoa implements PessoaJuridica, Comparable<Fornecedor> {
    private String insumo;
    private String cnpj;
    private double quantidadeDisponivel;
    private BigDecimal valorUnitario;

    public Fornecedor(String nome, String documento) {
        super(nome, documento);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        this.documento = cnpj;
    }

    @Override
    public String getCnpj() {
        return this.cnpj;
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

    @Override
    public int compareTo(Fornecedor o) {
        return this.getValorUnitario().compareTo(o.getValorUnitario());
    }
}
