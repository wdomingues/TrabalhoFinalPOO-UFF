package com.company.service;

import com.company.domain.CatalogoInsumo;
import com.company.domain.Fornecedor;
import com.company.domain.Insumo;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GerenciadorCatalogo {
    private CatalogoInsumo catalogo;

    public CatalogoInsumo gerarCatalogo() {
        if (this.catalogo == null) {
            try {
                String json
                        = String.join(" ",
                        Files.readAllLines(
                                Paths.get("./src/com/company/mock.json"),
                                StandardCharsets.UTF_8)
                );
                Fornecedor[] fornecedorList;
                fornecedorList = new Gson().fromJson(json, Fornecedor[].class);

                ArrayList<Insumo> insumos = new ArrayList<Insumo>();
                Insumo insumo;

                for (Fornecedor f :
                        fornecedorList) {
                    insumo = insumos.stream().filter(ins -> f.getInsumo().equals(ins.getNome())).findAny().orElse(null);
                    if (insumo == null) {
                        insumo = new Insumo();
                        insumo.setNome(f.getInsumo());
                        insumo.addFornecedor(f);
                        insumos.add(insumo);
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
        }
        else
        {
            ArrayList<Insumo> insumos =this.catalogo.getInsumos();
            for (int i = 0; i < insumos.stream().count(); i++) {
                for (Fornecedor fornecedor :
                        insumos.get(i).getFornecedores()) {
                    if(!validadorFornecedor(fornecedor))
                        insumos.get(i).removeFornecedor(fornecedor);
                }
            }
        }
        return this.catalogo;
    }

    private boolean validadorFornecedor(Fornecedor fornecedor) {
        return fornecedor.getQuantidadeDisponivel() > 0;
    }

}
