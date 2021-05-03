package com.company.service;

import com.company.domain.Andar;
import com.company.domain.Edificacao;

import java.util.Scanner;

public class GerenciadorEdificacao {
    private Edificacao edificacao;
    private int ordem = 1;

    public Edificacao projetarEdificacao(Edificacao edificacao) {
        try {
            this.edificacao = (Edificacao) edificacao.cloneEdificacao();
            this.edificacao.setOrdem(this.ordem);
            this.ordem++;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this.edificacao;
    }

    public Edificacao projetarEdificacao() {
        Scanner scanner = new Scanner(System.in);
        int qtdAndares, prazoDias;

        System.out.println("\nDigite quantos andares vai ter a sua " + Helpers.getOrdinal(this.ordem, false) + " edficação:");
        qtdAndares = Helpers.validaInteiroPositivo();
        this.edificacao = new Edificacao(qtdAndares);
        String proximoIgual = "N";
        Andar a = null;

        for (int i = 0; i < qtdAndares; i++) {
            if (proximoIgual.equalsIgnoreCase("S")) {
                //a.setNumeroAndar(i+1);
                this.edificacao.addAndar(a); //adiciona andar igual ao anterior
            } else {
                a = new GerenciadorAndar().projetarAndar(i + 1);
                //a.setNumeroAndar(i + 1);
                this.edificacao.addAndar(a);
            }

            if (i < qtdAndares - 1) {
                System.out.println("\nO " + Helpers.getOrdinal(i + 2, true) + " andar será igual a este? (S/N)");
                proximoIgual = scanner.nextLine();
            }
        }
        this.edificacao.setOrdem(this.ordem);
        this.ordem++;

        return this.edificacao;
    }
}
