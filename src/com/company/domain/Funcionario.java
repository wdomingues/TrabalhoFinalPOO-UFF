package com.company.domain;

public class Funcionario extends Pessoa implements PessoaFisica{
    private String cargo;
    private float salarioHora;

    public Funcionario(String nome, String documento) {
        super(nome, documento);
    }


    @Override
    public String getCpf() {
        return this.documento;
    }
    @Override
    public String getNome() {
        return this.nome;
    }
}
