package com.company.service;

import com.company.domain.Cliente;

import java.util.Scanner;

public class GerenciadorCliente {

    private Cliente cliente;


    public Cliente getCliente() {
        return cliente;
    }

    public Cliente cadastrar() {
        Scanner scanner = new Scanner(System.in);
        String nome,cpf;

        //TODO identificar se o cliente é pessoa física

        System.out.println("Digite o nome do Cliente: ");
        nome = scanner.nextLine();
        System.out.println("Digite o CPF do Cliente: ");
        cpf = scanner.nextLine();

        this.cliente = new Cliente(nome, cpf);

        return this.cliente;
    }
}
