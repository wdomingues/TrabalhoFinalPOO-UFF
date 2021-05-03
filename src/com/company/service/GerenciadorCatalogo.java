package com.company.service;

import com.company.domain.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorCatalogo {
    private CatalogoInsumo catalogo;
    private Funcionario[] funcionarios;

    public CatalogoInsumo gerarCatalogo() {
        if (this.catalogo == null) {
            try {
                String json
                        = String.join(" ",
                        Files.readAllLines(
                                Paths.get("./mock-fornecedores.json"),
                                StandardCharsets.UTF_8)
                );
                Fornecedor[] fornecedorList;
                fornecedorList = new Gson().fromJson(json, Fornecedor[].class);

                ArrayList<Insumo> insumos = new ArrayList<Insumo>();
                Insumo insumo;
                Map<Insumo, Integer> insumosQuantidade = new HashMap<>();

                for (Fornecedor f :
                        fornecedorList) {
                    // Verifica se insumo ja foi inserido na lista de insumos que vai virar o catalogo
                    insumo = insumos.stream().filter(ins -> f.getInsumo().equals(ins.getNome())).findAny().orElse(null);
                    if (insumo == null) {
                        insumo = new Insumo();
                        insumo.setNome(f.getInsumo());
                        insumo.addFornecedor(f);
                        insumos.add(insumo);
                        insumosQuantidade.put(insumo, (int) f.getQuantidadeDisponivel());
                    } else {
                        for (int i = 0; i < insumos.stream().count(); i++) {
                            if (insumos.get(i).getNome().equals(f.getInsumo()))
                                insumos.get(i).addFornecedor(f);
                        }
                    }
                }

                this.catalogo = new CatalogoInsumo(insumos);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            ArrayList<Insumo> insumos = this.catalogo.getInsumos();
            for (int i = 0; i < insumos.stream().count(); i++) {
                for (Fornecedor fornecedor :
                        insumos.get(i).getFornecedores()) {
                    if (!validadorFornecedor(fornecedor))
                        insumos.get(i).removeFornecedor(fornecedor);
                }
            }
            this.catalogo.setInsumos(insumos);
        }
        return this.catalogo;
    }

    public Funcionario[] identificarFuncionariosDisponiveis() {
        if (this.funcionarios == null) {
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
                this.funcionarios = funcionarios;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            this.funcionarios = Arrays.stream(this.funcionarios).filter(funcionario -> funcionario.isDisponivel()).sorted((o1, o2) -> o1.compareTo(o2)).toArray(Funcionario[]::new);
        }

        var listF = Arrays.stream(this.funcionarios).collect(Collectors.toList());
        Collections.shuffle(listF);
        this.funcionarios = listF.toArray(Funcionario[]::new);
        return this.funcionarios;
    }


    public void vincularFuncionarioProjeto(Funcionario funcionario, Projeto projeto) {

    }

    public void vincularInsumoProjeto(Insumo insumo, Projeto projeto) {

    }


    private boolean validadorFornecedor(Fornecedor fornecedor) {
        return fornecedor.getQuantidadeDisponivel() > 0;
    }

}
