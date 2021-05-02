package com.company.domain;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maria
 */
public class Cliente extends Pessoa implements PessoaFisica {

    public Cliente(String nome, String documento) {
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
