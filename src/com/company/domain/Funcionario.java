package com.company.domain;

import java.math.BigDecimal;

public class Funcionario extends Pessoa implements PessoaFisica, Comparable<Funcionario>{
    private BigDecimal salarioHora;
    private String cpf;
    private boolean disponivel;

    public Funcionario(String nome, String documento) {
        super(nome, documento);
    }

    public void setCpf(String cnpj) {
        this.cpf = cnpj;
    }

    public BigDecimal getSalarioHora() {
        return this.salarioHora;
    }

    public void setSalarioHora(BigDecimal salarioHora) {
        this.salarioHora = salarioHora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String getCpf() {
        return this.cpf;
    }
    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int compareTo(Funcionario o) {
        return this.getSalarioHora().compareTo(o.getSalarioHora());
    }

}
