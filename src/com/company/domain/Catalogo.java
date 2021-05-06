package com.company.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private ArrayList<Insumo> insumos = new ArrayList<>();

    private Fornecedor[] fornecedores;
    private Funcionario[] funcionarios;

    public Catalogo() {

    }

    public Catalogo(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public Catalogo(Fornecedor[] fornecedores) {
        setFornecedores(fornecedores);
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public Funcionario[] getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario[] funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Fornecedor[] getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedor[] fornecedores) {
        Map<Insumo, Integer> insumosQuantidade = new HashMap<>();

        if (fornecedores != null)
            for (Fornecedor f :
                    fornecedores) {
                // Verifica se insumo ja foi inserido na lista de insumos que vai virar o catalogo
                Insumo insumo = insumos.stream().filter(ins -> f.getInsumo().equals(ins.getNome())).findAny().orElse(null);
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
        this.setInsumos(insumos);
        this.fornecedores = fornecedores;
    }
}
