package com.company.service;

import com.company.domain.Edificacao;
import com.company.domain.Projeto;

public class GerenciadorProjeto {
    private Projeto projeto;

    public Projeto cadastrar() {
        this.projeto = new Projeto();
        GerenciadorEdificacao gerenciadorEdificacao = new GerenciadorEdificacao();
        Edificacao edificacao = gerenciadorEdificacao.ProjetarEdificacao();


        //TODO: pensar em deslocar para Gerenciador de projeto
        System.out.println("Digite o prazo máximo que você deseja:");
        //this.edificacao.setPrazoDias(scanner.nextInt());

        //TODO: calcular baseado em prazo, consid dias de trab x nFuncionarios para exec comodo



        return this.projeto;
    }
}
