package com.company.service;

import com.company.domain.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorCatalogo {
    private Catalogo catalogo;

    public Catalogo gerarCatalogo() {
        try {
            String json
                    = String.join(" ",
                    Files.readAllLines(
                            Paths.get("./mock-fornecedores.json"),
                            StandardCharsets.UTF_8)
            );
            Fornecedor[] fornecedorList;
            fornecedorList = new Gson().fromJson(json, Fornecedor[].class);

            this.catalogo = new Catalogo(fornecedorList);

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return this.catalogo;
    }

    public Funcionario[] identificarFuncionariosDisponiveis() {
        if (this.catalogo == null)
            this.catalogo = gerarCatalogo();
        if (this.catalogo.getFuncionarios() == null) {
            try {
                String json
                        = String.join(" ",
                        Files.readAllLines(
                                Paths.get("./mock-funcionarios.json"),
                                StandardCharsets.UTF_8)
                );
                Funcionario[] funcionarios;
                funcionarios = new Gson().fromJson(json, Funcionario[].class);

                funcionarios = Arrays.stream(funcionarios).filter(funcionario -> funcionario.isDisponivel()).sorted((o1, o2) -> o1.compareTo(o2)).toArray(Funcionario[]::new);
                this.catalogo.setFuncionarios(funcionarios);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            this.catalogo.setFuncionarios(Arrays.stream(this.catalogo.getFuncionarios()).filter(funcionario -> funcionario.isDisponivel()).sorted((o1, o2) -> o1.compareTo(o2)).toArray(Funcionario[]::new));
        }

        var listF = Arrays.stream(this.catalogo.getFuncionarios()).collect(Collectors.toList());
        Collections.shuffle(listF);
        this.catalogo.setFuncionarios(listF.toArray(Funcionario[]::new));
        return this.catalogo.getFuncionarios();
    }


    public void vincularInsumoProjeto(Insumo insumo, Projeto projeto) {
        if (this.catalogo == null)
            this.catalogo = gerarCatalogo();
        Fornecedor fornecedor = insumo.getFornecedores().get(0);
        fornecedor.setQuantidadeDisponivel(fornecedor.getQuantidadeDisponivel() + projeto.getInsumosNecessarios().get(insumo.getNome()));
        atualizarCatalogo(this.catalogo, fornecedor, null);
        projeto.addInsumo(insumo);
    }


    public void vincularFuncionarioProjeto(Funcionario funcionario, Projeto projeto) {
        if (this.catalogo == null)
            this.catalogo = gerarCatalogo();
        funcionario.setDisponivel(false);
        atualizarCatalogo(this.catalogo, null, funcionario);
        projeto.addFuncionario(funcionario);
    }


    public Catalogo atualizarCatalogo(Catalogo catalogo, Fornecedor fornecedorAlterado,
                                      Funcionario funcionarioAlterado) {
        Fornecedor[] mapFornecedor = null;
        Funcionario[] mapFuncionario = null;
        try {
            // create Gson instance
            Gson gson = new Gson();
            Reader readerFornecedor = Files.newBufferedReader(Paths.get("./mock-fornecedores.json"));
            mapFornecedor = gson.fromJson(readerFornecedor, Fornecedor[].class);
            readerFornecedor.close();

            Reader readerFuncionario = Files.newBufferedReader(Paths.get("./mock-funcionarios.json"));
            mapFuncionario = gson.fromJson(readerFuncionario, Funcionario[].class);
            readerFuncionario.close();

            if (fornecedorAlterado != null) {
                if (mapFornecedor != null && mapFornecedor.length > 0)
                    Arrays.stream(mapFornecedor).forEach(p1 -> {
                        if (!p1.getCnpj().equals(fornecedorAlterado.getCnpj()) && !p1.getInsumo().equals(fornecedorAlterado.getInsumo()))
                            p1 = fornecedorAlterado;
                        //fornecedorList.add(p1);
                    });
                Writer writerFornecedor = Files.newBufferedWriter(Paths.get("./mock-fornecedores.json"));
                gson.toJson(mapFornecedor, writerFornecedor);
                writerFornecedor.close();
                catalogo.setFornecedores(mapFornecedor);
            }

            if (funcionarioAlterado != null) {
                if (mapFuncionario != null && mapFuncionario.length > 0)
                    Arrays.stream(mapFuncionario).forEach(p1 -> {
                        if (!p1.getCpf().equals(funcionarioAlterado.getCpf()) && !p1.getNome().equals(funcionarioAlterado.getNome()))
                            p1 = funcionarioAlterado;
                        //fornecedorList.add(p1);
                    });
                Writer writerFuncionario = Files.newBufferedWriter(Paths.get("./mock-funcionarios.json"));
                gson.toJson(mapFuncionario, writerFuncionario);
                writerFuncionario.close();
                catalogo.setFuncionarios(mapFuncionario);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return catalogo;
    }


    private boolean validadorFornecedor(Fornecedor fornecedor) {
        return fornecedor.getQuantidadeDisponivel() > 0;
    }

}
