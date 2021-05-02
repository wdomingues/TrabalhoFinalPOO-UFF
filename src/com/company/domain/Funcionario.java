/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.domain;

import com.company.domain.Pessoa;

/**
 *
 * @author Maria
 */
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
