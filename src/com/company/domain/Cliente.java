package com.company.domain;

public class Cliente extends Pessoa implements PessoaFisica, PessoaJuridica {

    private boolean isPessoaFisica = true;

    public Cliente(String nome, String documento) {
        super(nome, documento);
    }

    @Override
    public String getCpf() {
        return this.isPessoaFisica ? this.documento : null;
    }

    @Override
    public String getNome() {
        return this.isPessoaFisica ? this.nome : null;
    }

    @Override
    public String getCnpj() {
        return !this.isPessoaFisica ? this.documento : null;
    }

    @Override
    public String getRazaoSocial() {
        return !this.isPessoaFisica ? this.nome : null;
    }
}
