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

    public CatalogoInsumo gerarCatalogo() throws IOException {
        String json
                = String.join(" ",
                Files.readAllLines(
                        Paths.get("./src/com/company/mock.json"),
                        StandardCharsets.UTF_8)
        );
        Fornecedor[] fornecedorList;
        fornecedorList = new Gson().fromJson(json, Fornecedor[].class);

        for (Fornecedor f :
                fornecedorList) {
            System.out.println(f.getInsumo());
        }
        //
        // Criando lista de insumos disponiveis
        this.catalogo = new CatalogoInsumo(new ArrayList<Insumo>());
        return this.catalogo;
    }

    private boolean validadorFornecedor(Fornecedor fornecedor){
        return fornecedor.getQuantidadeDisponivel() > 0;
    }

}
