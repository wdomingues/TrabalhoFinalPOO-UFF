package com.company.service;

import com.company.domain.Andar;
import com.company.domain.Edificacao;

import java.util.Scanner;

public class GerenciadorEdificacao {
    private Edificacao edificacao;

    public Edificacao ProjetarEdificacao(){
        Scanner scanner = new Scanner(System.in);
        int qtdAndares, prazoDias; //TODO reavaliar necessidade de prazoDias ou -> Projeto

        System.out.println("Digite quantos andares vai ter a sua edficação:");
        qtdAndares = Helpers.validaInteiroPositivo();
        this.edificacao = new Edificacao(qtdAndares);
        String proximoIgual = "N";
        Andar a = null;

        for (int i = 0; i<qtdAndares; i++){
            if (proximoIgual.equalsIgnoreCase("S")){
                //a.setNumeroAndar(i+1);
                this.edificacao.addAndar(a); //adiciona andar igual ao anterior
            } else{
                a = new GerenciadorAndar().projetarAndar(i+1);
                //a.setNumeroAndar(i + 1);
                this.edificacao.addAndar(a);
            }

            if ( i < qtdAndares-1){
                System.out.printf("O " + Helpers.getOrdinal(i+2) +" andar será igual a este? (S/N)", i+2);
                proximoIgual = scanner.nextLine();
            }
        }

        return  this.edificacao;
    }
}
