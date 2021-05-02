package com.company.service;

import com.company.domain.Comodo;
import com.company.domain.Parede;

import java.util.Scanner;

public class GerenciadorComodo {
    private Comodo comodo;

    public Comodo projetarComodo(){
        Scanner scanner = new Scanner(System.in);
        int qtdParedes;

        //TODO: reavaliar necessidade de qtdparedes>4
        System.out.println("Digite quantas paredes vai ter este comodo:");
        qtdParedes = scanner.nextInt();
        this.comodo = new Comodo(qtdParedes);
        String proximaIgual = "N";
        Parede p = null;
        for (int i = 0; i<qtdParedes; i++){
            if (proximaIgual.equalsIgnoreCase("S")){
                this.comodo.addParede(p); //adiciona parede igual ao anterior
            } else{
                p = new Parede();
                System.out.printf("Parede %d: Qual é a largura desta parede?", i+1);
                p.setLargura(scanner.nextFloat());
                System.out.printf("Parede %d: Qual é a largura desta parede?", i+1);
                p.setAltura(scanner.nextFloat());

                if (i==0) {
                    System.out.printf("Todas as paredes do(a) %s são iguais? (S/N)", i + 1);
                    String todasIguais = scanner.nextLine();
                    if (todasIguais.substring(0, 1).equalsIgnoreCase("S")) {
                        for (int j = 0; j < qtdParedes; j++) {
                            this.comodo.addParede(p);
                        }
                        this.comodo.addParede(this.comodo.criaLaje());
                        return this.comodo;
                    }
                }
                this.comodo.addParede(this.comodo.criaLaje());
                this.comodo.addParede(p);
            }

            if ( i < qtdParedes-1){
                System.out.printf("A %da. parede será igual a esta? (S/N)", i+2);
                proximaIgual = scanner.nextLine();
            }
        }
        return this.comodo;
    }

}
