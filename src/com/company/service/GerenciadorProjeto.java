package com.company.service;

import com.company.domain.Projeto;

import java.util.Scanner;

public class GerenciadorProjeto {
    private Projeto projeto;

    public Projeto cadastrar() {
        Scanner scanner = new Scanner(System.in);
        int andares;

        this.projeto = new Projeto();

        System.out.println("Onde fica ");
        System.out.println("Digite quantos andares:");
        andares = scanner.nextInt();
        System.out.println("Digite o prazo máximo que você deseja:");
        andares = scanner.nextInt();

        return this.projeto;
    }
}
