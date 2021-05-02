package com.company.service;

import com.company.domain.Edificacao;
import com.company.domain.Projeto;

public class GerenciadorProjeto {
    private Projeto projeto;

    public Projeto cadastrar() {
        this.projeto = new Projeto();
        GerenciadorEdificacao gerenciadorEdificacao = new GerenciadorEdificacao();
        Edificacao edificacao = gerenciadorEdificacao.ProjetarEdificacao();


        return this.projeto;
    }
}
