package com.company.service;

import com.company.domain.CatalogoInsumo;
import com.company.domain.Fornecedor;

public class GerenciadorCatalogo {
    private CatalogoInsumo catalogo;

    public CatalogoInsumo gerarCatalogo(){
        // TODO Leitura do mock.json
        //
        // Criando lista de insumos disponiveis
        return this.catalogo;
    }

    private boolean validadorFornecedor(Fornecedor fornecedor){
        return fornecedor.getQuantidadeDisponivel() > 0;
    }

}
