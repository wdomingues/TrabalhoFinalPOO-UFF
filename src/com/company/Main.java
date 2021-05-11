package com.company;

import com.company.domain.Catalogo;
import com.company.domain.Cliente;
import com.company.domain.Orcamento;
import com.company.domain.Projeto;
import com.company.exceptions.CPFInvalidoException;
import com.company.helper.SituacaoProjeto;
import com.company.service.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        boolean sair = false;
        String opcaoSelecionada, input = new String();
        Scanner scanner = new Scanner(System.in);
        GerenciadorCatalogo gerenciadorCatalogo = new GerenciadorCatalogo();
        Catalogo catalogo = null;
        try {
            catalogo = gerenciadorCatalogo.gerarCatalogo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cliente cliente = null;
        Orcamento orcamento = null;

        GerenciadorCliente gerenciadorCliente = new GerenciadorCliente();
        GerenciadorProjeto gerenciadorProjeto = new GerenciadorProjeto();

        while (!sair) {
            orcamento = null;
            Helpers.clear();
            Projeto[] projetos = new Projeto[0];
            try {
                projetos = gerenciadorProjeto.recuperaProjetos();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.flush();
            System.out.print("\n\nCalcProj - A Calculadora de Projetos da Constrora NewCo"); //aslam
            System.out.println("\n\nEscolha uma opção (ou tecle apenas enter para sair): ");
            System.out.println("\n1 - Cadastrar Projeto");
            System.out.println("2 - Cadastrar Cliente");
            System.out.print("\b\b\b");
            if (projetos != null && Arrays.stream(projetos).count() > 0) {
                System.out.println("3 - Selecionar Projeto");
            }
            opcaoSelecionada = scanner.nextLine();

            switch (opcaoSelecionada) {
                case "1":
                    System.out.println("\nJá existe cliente cadastrado? (S/N)");
                    opcaoSelecionada = scanner.nextLine();
                    try {
                        if (opcaoSelecionada.equalsIgnoreCase("sim") || opcaoSelecionada.equalsIgnoreCase("S")) {
                            cliente = gerenciadorCliente.getCliente();
                        } else {
                            try {
                                cliente = gerenciadorCliente.cadastrar();
                            } catch (CPFInvalidoException exc){
                                exc.printStackTrace();
                            }
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Projeto projeto = gerenciadorProjeto.cadastrar();
                        try{
                            projeto.setCliente(cliente);
                        } catch(ExceptionInInitializerError e){
                            e.printStackTrace();
                            projeto.setCliente(new Cliente());
                        }
                        try {
                            orcamento = validaProjetoGeraOrcamento(catalogo, gerenciadorProjeto, projeto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "2":
                    try {
                        cliente = gerenciadorCliente.cadastrar();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    if (projetos == null || Arrays.stream(projetos).count() == 0) {
                        System.out.println("Não há projeto para selecionar");
                    } else {
                        SituacaoProjeto[] situacoes = SituacaoProjeto.values();
                        System.out.println("\nEscolha a situação do(s) projeto(s) para exibi-los: ");
                        System.out.println("1 - Todos os Projetos");
                        for (SituacaoProjeto situacao : situacoes)
                            System.out.println((situacao.getNumero() + 1) + " - " + situacao.getOrdinal());
                        int res = Helpers.validaInteiroPositivo();
                        System.out.println("\n");
                        Projeto projeto;//aslam
                        if (res == 1)
                            projeto = gerenciadorProjeto.selecionarProjeto(projetos, situacoes);
                        else {
                            projetos = Arrays.stream(projetos).filter(p -> p.getSituacao().equals(situacoes[res - 2])).toArray(Projeto[]::new);
                            projeto = gerenciadorProjeto.selecionarProjeto(projetos, Arrays.stream(situacoes).filter(s -> (s.getNumero() == (res - 2))).toArray(SituacaoProjeto[]::new));
                        }
                        try {
                            orcamento = validaProjetoGeraOrcamento(catalogo, gerenciadorProjeto, projeto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //System.out.flush(); //aslam
                        imprimeOrcamento(orcamento);
                    }
                    break;

                case "":
                    sair = true;
                    break;
            }
            if (orcamento != null) {

            }

            //ValidadorProjeto
            //GeradorContrato contrato = new GeradorContrato(projeto, cliente, );
        }
    }

    private static void imprimeOrcamento(Orcamento orcamento) {//aslam
        System.out.println("Orçamento");

        System.out.println("\tItens");
        System.out.println("\t\t"+orcamento.getItens());

        System.out.println("\tValor:");
        System.out.println("\t\t"+orcamento.getValorMovel().toPlainString());

    }

    private static Orcamento validaProjetoGeraOrcamento (Catalogo catalogo, GerenciadorProjeto gerenciadorProjeto, Projeto projeto) throws IOException{
        Orcamento orcamento = null;
        if (ValidadorProjeto.validar(projeto, catalogo)) {
            orcamento = CalculadoraOrcamento.calcula(projeto);

        } else {
            new Scanner(System.in).next();
            System.out.println("Vamos salvar o projeto para aguardar a atualização da lista de insumos através dos Fornecedores.");
            new Scanner(System.in).next();
            gerenciadorProjeto.salvaProjetoEmEspera(projeto);
        }
        return orcamento;
    }

}
