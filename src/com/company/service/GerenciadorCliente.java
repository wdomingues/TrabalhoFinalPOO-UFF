package com.company.service;

import com.company.domain.Cliente;
import com.company.exceptions.DocumentoInvalidoException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GerenciadorCliente {

    private Cliente cliente;


    public Cliente getCliente() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean continua = true;
        while (continua) {
            System.out.println("Digite o nome ou CPF do Cliente: ");
            input = scanner.nextLine();
            this.cliente = usaCliente(input);
            if (this.cliente == null) {
                System.out.println("Inválido!");
                System.out.println("Digite um nome ou CPF válido");

            } else
                continua = false;
        }
        return this.cliente;
    }

    public Cliente cadastrar() throws IOException, DocumentoInvalidoException { //usando a primeira exception criada
        Scanner scanner = new Scanner(System.in);
        String nome, documento;

        //TODO identificar se o cliente é pessoa física

        System.out.println("Digite o nome do Cliente: ");
        nome = scanner.nextLine();
        System.out.println("Digite o CPF do Cliente: ");
        documento = scanner.nextLine();
        String documentoLimpo = documento.replace(".", "").replace("-", "").replace("/", "");
        if ((documentoLimpo.matches("\\d{11}"))
                || (documentoLimpo.matches("\\d{14}"))) { //especificar regex para CPF e para CNPJ (qtd de digitos)
            this.cliente = new Cliente(nome, documentoLimpo);
            salvaCliente(this.cliente);
        } else {
            throw new DocumentoInvalidoException("Documento Inválido. Tente Novamente");
        }

        return this.cliente;
    }

    public Cliente verificarClientes(String nome, String cpf) {
        return new Cliente(nome, cpf);
    }

    private Cliente[] salvaCliente(Cliente cliente) throws IOException{
        Cliente[] map = null;
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
        return map;
    }

    private Cliente usaCliente(String dadoBusca) throws IOException{
        Cliente[] map = null;
        // create Gson instance
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("./mock-clientes.json"));
        map = gson.fromJson(reader, Cliente[].class);
        reader.close();
        Cliente cliente = Arrays.stream(map).filter(c -> c.getNome().equals(dadoBusca) || c.getCpf().equals(dadoBusca)).findFirst().orElse(null);
        return cliente;
    }
}
