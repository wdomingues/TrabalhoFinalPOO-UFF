package com.company.service;

import com.company.domain.Cliente;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GerenciadorCliente {

    private Cliente cliente;


    public Cliente getCliente() {
        return cliente;
    }

    public Cliente cadastrar() {
        Scanner scanner = new Scanner(System.in);
        String nome, cpf;

        //TODO identificar se o cliente é pessoa física

        System.out.println("Digite o nome do Cliente: ");
        nome = scanner.nextLine();
        System.out.println("Digite o CPF do Cliente: ");
        cpf = scanner.nextLine();

        this.cliente = new Cliente(nome, cpf);

        salvaCliente(this.cliente);
        return this.cliente;
    }

    public Cliente verificarClientes(String nome, String cpf) {
        return new Cliente(nome, cpf);
    }

    public Cliente[] salvaCliente(Cliente cliente) {
        Cliente[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();
            // cria Cliente list
            ArrayList<Cliente> ClienteList = new ArrayList<Cliente>();
            ClienteList.add(cliente);
            Reader reader = Files.newBufferedReader(Paths.get("./mock-Clientes.json"));
            map = gson.fromJson(reader, Cliente[].class);
            reader.close();

            if (map != null && map.length > 0)
                Arrays.stream(map).forEach(p1 -> {
                    ClienteList.add(p1);
                });
            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("./mock-Clientes.json"));
            // convert Clientes object to JSON file
            gson.toJson(ClienteList, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }
}
