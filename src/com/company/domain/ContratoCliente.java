package com.company.domain;

public class ContratoCliente extends Contrato {
    public ContratoCliente(Cliente cliente, Projeto projeto) {
        this.cliente = cliente;
        this.projeto = projeto;
    }
}
