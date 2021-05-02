/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.domain;

public class Fornecedor extends Pessoa implements PessoaJuridica {
    private Insumo insumo;
    private int quantidadeDisponivel;

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
}
