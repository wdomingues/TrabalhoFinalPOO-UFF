package com.company.service;

import com.company.domain.Edificacao;

import java.util.Scanner;

public class GerenciadorEdificacao {
    private Edificacao edificacao;

    public Edificacao ProjetarEdificacao(){
        Scanner scanner = new Scanner(System.in);
        int andares, prazoDias;

        System.out.println("Digite quantos andares:");
        andares = scanner.nextInt();
        this.edificacao = new Edificacao(andares);
        for (int i = 0; i<andares; i++){
            this.edificacao.addAndar(new GerenciadorAndar().projetarAndar());
        }

        System.out.println("Digite o prazo máximo que você deseja:");
        prazoDias = scanner.nextInt();
        
        return  this.edificacao;
    }
}
