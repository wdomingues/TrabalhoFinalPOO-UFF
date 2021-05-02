package com.company.service;

import com.company.domain.Andar;
import com.company.domain.Comodo;

import java.util.Scanner;

public class GerenciadorAndar {
    private Andar andar;

    public Andar projetarAndar(){
        // DONE Codigo para pegar informações do andar
        Scanner scanner = new Scanner(System.in);
        int qtdComodos;

        System.out.println("Digite quantos comodos vai ter este andar:");
        qtdComodos = scanner.nextInt();
        this.andar = new Andar(qtdComodos);
        String proximoIgual = "N";
        Comodo c = null;
        for (int i = 0; i<qtdComodos; i++){
            if (proximoIgual.equalsIgnoreCase("S")){
                System.out.println("Que cômodo é este?");
                c.setNomeComodo(scanner.nextLine());
                this.andar.addComodo(c); //adiciona cômodo igual ao anterior
            } else{
                c = new GerenciadorComodo().projetarComodo();
                System.out.println("Que cômodo é este?");
                c.setNomeComodo(scanner.nextLine());
                this.andar.addComodo(c);
            }

            if ( i < qtdComodos-1){
                System.out.printf("O %do. comodo será igual a este? (S/N)", i+2);
                proximoIgual = scanner.nextLine();
            }
        }

        // TODO Criar o projetarComodo no GerenciadorComodo
        // DONE Perguntar se o próximo andar (se houver) será igual ao anterior
        return this.andar;
    }
}

