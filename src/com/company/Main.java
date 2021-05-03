package com.company;

import com.company.domain.CatalogoInsumo;
import com.company.domain.Cliente;
import com.company.domain.Orcamento;
import com.company.domain.Projeto;
import com.company.helper.SituacaoProjeto;
import com.company.service.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean sair = false;
        String opcaoSelecionada = new String();
        Scanner scanner = new Scanner(System.in);
        GerenciadorCatalogo gerenciadorCatalogo = new GerenciadorCatalogo();
        CatalogoInsumo catalogo = gerenciadorCatalogo.gerarCatalogo();
        Cliente cliente;

        GerenciadorCliente gerenciadorCliente = new GerenciadorCliente();
        //
        gerenciadorCliente.cadastrar();
        //
        GerenciadorProjeto gerenciadorProjeto = new GerenciadorProjeto();

        while (!sair) {
            Projeto[] projetos = gerenciadorProjeto.recuperaProjetos();
            System.out.flush();
            System.out.println("\nEscolha uma opção (ou tecle enter para sair): ");
            System.out.println("1 - Cadastrar Projeto");
            System.out.println("2 - Cadastrar Cliente");
            if (projetos != null && Arrays.stream(projetos).count() > 0) {
                System.out.println("3 - Selecionar Projeto");
            }
            opcaoSelecionada = scanner.nextLine();

            switch (opcaoSelecionada) {
                case "1":
                    System.out.println("Já existe cliente cadastrado? (S/N)");
                    // VALIDAR A RESPOSTA
                    cliente = gerenciadorCliente.cadastrar();

                    Projeto projeto = gerenciadorProjeto.cadastrar();

                    if (ValidadorProjeto.validar(projeto, catalogo)) {
                        Orcamento orcamento = CalculadoraOrcamento.calcula(projeto);

                    } else
                        gerenciadorProjeto.salvaProjetoEmEspera(projeto);

                    break;
                case "2":
                    cliente = gerenciadorCliente.cadastrar();
                    break;
                case "3":
                    if (projetos == null || Arrays.stream(projetos).count() == 0) {
                        System.out.println("Não há projeto para selecionar");
                    } else {
                        System.out.println("\nEscolha a situação do(s) projeto(s) para exibi-los: ");
                        System.out.println("1 - Todos os Projetos");
                        System.out.println((SituacaoProjeto.AGUARDANDO.getNumero() + 1) + " - " + SituacaoProjeto.AGUARDANDO.getOrdinal());
                        System.out.println((SituacaoProjeto.PENDENTE.getNumero() + 1) + " - " + SituacaoProjeto.PENDENTE.getOrdinal());
                        System.out.println((SituacaoProjeto.REVISAO.getNumero() + 1) + " - " + SituacaoProjeto.REVISAO.getOrdinal());
                        System.out.println((SituacaoProjeto.APROVADO.getNumero() + 1) + " - " + SituacaoProjeto.APROVADO.getOrdinal());
                        System.out.println((SituacaoProjeto.FINALIZADO.getNumero() + 1) + " - " + SituacaoProjeto.FINALIZADO.getOrdinal());
                        Arrays.stream(projetos).filter(p -> p.getSituacao().equals(SituacaoProjeto.AGUARDANDO));
                    }
                    break;
                case "":
                    sair = true;
                    break;
            }


            //ValidadorProjeto
            //GeradorContrato contrato = new GeradorContrato(projeto, cliente, );
        }
    }

}
