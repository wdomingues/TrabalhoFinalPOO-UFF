package com.company.domain;

public class ContratoFornecedor extends Contrato{

    private int quantidade;

    public ContratoFornecedor(Fornecedor fornecedor, int quantidade) {
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
    }
}
