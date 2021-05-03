package com.company.domain;

public class Funcionario extends Pessoa implements PessoaFisica, Comparable<Funcionario>{
    private float salarioHora;
    private String cpf;
    private boolean disponivel;

    public Funcionario(String nome, String documento) {
        super(nome, documento);
    }

    public void setCpf(String cnpj) {
        this.cpf = cnpj;
    }

    public float getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(float salarioHora) {
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
        return this.documento;
    }
    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int compareTo(Funcionario o) {
        if (this.getSalarioHora() < o.getSalarioHora())
            return 1;
        else
        if (this.getSalarioHora() > o.getSalarioHora())
            return -1;
        else
            return 0;
    }

}
