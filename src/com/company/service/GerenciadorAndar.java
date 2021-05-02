package com.company.service;

import com.company.domain.Andar;

import java.util.Scanner;

public class GerenciadorAndar {
    private Andar andar;

    public Andar projetarAndar(int numeroAndar){
        // DONE Codigo para pegar informações do andar
        Scanner scanner = new Scanner(System.in);
        int mtQddLaje, mtQddParede, colunas;

        String andarOrdinal = Helpers.getOrdinal(numeroAndar);
        System.out.println("Digite quantos metros quadrados de laje (chão e teto) \no "+ andarOrdinal + " andar deve possuir:");
        mtQddLaje = scanner.nextInt();
        System.out.println("\nDigite quantos metros quadrados de paredes (externas e internas) \no "+ andarOrdinal + " andar deve possuir:");
        mtQddParede = scanner.nextInt();
        System.out.println("\nDigite quantas colunas de sustentação \no "+ andarOrdinal + " andar deve possuir:");
        colunas = scanner.nextInt();
        this.andar = new Andar(mtQddLaje,mtQddParede,colunas);

        return this.andar;
    }
}

