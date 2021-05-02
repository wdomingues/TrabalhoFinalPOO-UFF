package com.company;

import com.company.domain.*;
import com.company.service.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //lerFornecedores();
        GerenciadorCliente gerenciadorCliente = new GerenciadorCliente();
        GerenciadorProjeto gerenciadorProjeto = new GerenciadorProjeto();

        Cliente cliente = gerenciadorCliente.cadastrar();
//        Cliente cliente = new Cliente("Danilo","123123");
          Projeto projeto = gerenciadorProjeto.cadastrar();
//        Projeto projeto = new Projeto();
//        projeto.setEdificacao(new Edificacao(2));

        if(ValidadorProjeto.validar(projeto)){
            Orcamento orcamento = CalculadoraOrcamento.calcula(projeto);

        }

        //ValidadorProjeto
        //GeradorContrato contrato = new GeradorContrato(projeto, cliente, );
    }

}
