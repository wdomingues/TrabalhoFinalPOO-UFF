package com.company.service;

import com.company.domain.Andar;

import java.util.Scanner;

public class GerenciadorAndar {
    private Andar andar;

    public Andar projetarAndar(int numeroAndar){
        // DONE Codigo para pegar informações do andar
        Scanner scanner = new Scanner(System.in);
        int mtQddLaje, mtQddParede, colunas;

        String andarOrdinal = Helpers.getOrdinal(numeroAndar, true);
        System.out.println("\nDigite quantos metros quadrados de laje (chão e teto) \no "+ andarOrdinal + " andar deve possuir:");
        mtQddLaje = Helpers.validaInteiroPositivo();
        System.out.println("\nDigite quantos metros quadrados de paredes (externas e internas) \no "+ andarOrdinal + " andar deve possuir:");
        mtQddParede = Helpers.validaInteiroPositivo();
        System.out.println("\nDigite quantas colunas de sustentação \no "+ andarOrdinal + " andar deve possuir:");
        colunas = Helpers.validaInteiroPositivo();
        this.andar = new Andar(mtQddLaje,mtQddParede,colunas);

        return this.andar;
    }
}

