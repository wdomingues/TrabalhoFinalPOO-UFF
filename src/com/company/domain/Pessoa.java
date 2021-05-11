package com.company.domain;

public abstract class Pessoa {
    protected String nome;
    protected String documento;

    public Pessoa(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public Pessoa() {

    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
