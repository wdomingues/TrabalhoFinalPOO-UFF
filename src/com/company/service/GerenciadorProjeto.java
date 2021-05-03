package com.company.service;

import com.company.domain.Edificacao;
import com.company.domain.Projeto;

import java.util.Scanner;

public class GerenciadorProjeto {
    private Projeto projeto;

    public Projeto cadastrar() {
        Scanner scanner = new Scanner(System.in);
        String resposta = "s";
        this.projeto = new Projeto();
        GerenciadorEdificacao gerenciadorEdificacao = new GerenciadorEdificacao();
        Edificacao edificacao = gerenciadorEdificacao.ProjetarEdificacao();
        this.projeto.addEdificacao(edificacao);

        while(resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim")) {
            System.out.println("Alguma outra edificação? (S/N)");
            resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim")) {
                System.out.println("As medidas serão iguais a anterior? (S/N)");
                resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim"))
                    this.projeto.addEdificacao(edificacao);
                else {
                    edificacao = gerenciadorEdificacao.ProjetarEdificacao();
                    this.projeto.addEdificacao(edificacao);
                }
            }
        }





        //TODO: pensar em deslocar para Gerenciador de projeto
        //System.out.println("Digite o prazo máximo que você deseja:");
        //this.edificacao.setPrazoDias(scanner.nextInt());

        //TODO: calcular baseado em prazo, consid dias de trab x nFuncionarios para exec comodo



        return this.projeto;
    }
}
