package com.company;

import com.company.domain.Catalogo;
import com.company.domain.Cliente;
import com.company.domain.Orcamento;
import com.company.domain.Projeto;
import com.company.helper.SituacaoProjeto;
import com.company.service.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //new KeyEventExample();

        boolean sair = false;
        String opcaoSelecionada, input = new String();
        Scanner scanner = new Scanner(System.in);
        GerenciadorCatalogo gerenciadorCatalogo = new GerenciadorCatalogo();
        Catalogo catalogo = gerenciadorCatalogo.gerarCatalogo();
                Cliente cliente;
        Orcamento orcamento = null;

        GerenciadorCliente gerenciadorCliente = new GerenciadorCliente();
        GerenciadorProjeto gerenciadorProjeto = new GerenciadorProjeto();

//        String anim= "|/-\\";
//        for (int x =0 ; x < 100 ; x++) {
//            String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
//            System.out.write(data.getBytes());
//            Thread.sleep(100);
//        }

        while (!sair) {
            orcamento = null;
            Helpers.clear();
            Projeto[] projetos = GerenciadorProjeto.recuperaProjetos();
            System.out.flush();
            System.out.println("\n\nEscolha uma opção (ou tecle apenas enter para sair): ");
            System.out.println("\n1 - Cadastrar Projeto");
            System.out.println("2 - Cadastrar Cliente");
            if (projetos != null && Arrays.stream(projetos).count() > 0) {
                System.out.println("3 - Selecionar Projeto");
            }
            opcaoSelecionada = scanner.nextLine();

            switch (opcaoSelecionada) {
                case "1":
                    if(gerenciadorCliente.existeClienteCadastrado()) {
                        System.out.println("\nJá existe cliente cadastrado? (S/N)");
                        opcaoSelecionada = scanner.nextLine();
                        if (opcaoSelecionada.equalsIgnoreCase("sim") || opcaoSelecionada.equalsIgnoreCase("S")) {
                            cliente = gerenciadorCliente.getCliente();
                        } else
                            cliente = gerenciadorCliente.cadastrar();
                    }
                    else
                        cliente = gerenciadorCliente.cadastrar();
                    Projeto projeto = gerenciadorProjeto.cadastrar();
                    projeto.setCliente(cliente);

                    orcamento = validaProjetoGeraOrcamento(catalogo, gerenciadorProjeto, projeto);

                    break;
                case "2":
                    cliente = gerenciadorCliente.cadastrar();
                    break;
                case "3":
                    if (projetos == null || Arrays.stream(projetos).count() == 0) {
                        System.out.println("Não há projeto para selecionar");
                    } else {
                        SituacaoProjeto[] todasSituacoes = SituacaoProjeto.values();
                        SituacaoProjeto[] situacoes = new SituacaoProjeto[]{};
                        for (SituacaoProjeto situacoe : todasSituacoes) {
                            if(Arrays.stream(projetos).anyMatch(projota -> projota.getSituacao().equals(situacoe) )) {
                                var sit = Arrays.stream(situacoes).collect(Collectors.toList());
                                sit.add(situacoe);
                                situacoes = sit.toArray(SituacaoProjeto[]::new);
                            }
                        }
                        System.out.println("\nEscolha a situação do(s) projeto(s) para exibi-los: ");
                        System.out.println("1 - Todos os Projetos");
                        for (SituacaoProjeto situacao : situacoes)
                            System.out.println((Arrays.stream(situacoes).collect(Collectors.toList()).indexOf(situacao) + 2) + " - " + situacao.getOrdinal());
                        int res = Helpers.validaInteiroPositivo();
                        System.out.println("\n");
                        if (res == 1)
                            projeto = gerenciadorProjeto.selecionarProjeto(projetos, situacoes);
                        else if(res > 1) {
                            SituacaoProjeto[] finalSituacoes = situacoes;
                            projetos = Arrays.stream(projetos).filter(p -> p.getSituacao().equals(finalSituacoes[res - 2])).toArray(Projeto[]::new);
                            projeto = gerenciadorProjeto.selecionarProjeto(projetos, Arrays.stream(situacoes).filter(s -> (s.getNumero() == (res - 2))).toArray(SituacaoProjeto[]::new));
                            orcamento = validaProjetoGeraOrcamento(catalogo, gerenciadorProjeto, projeto);
                        }
                        System.out.println("Projeto nao existe");
                    }
                    break;
                case "":
                    sair = true;
                    break;
            }
            if (orcamento != null) {
                gerenciadorProjeto.avancaProjetoOrcamento(orcamento);

            }

            //ValidadorProjeto
            //GeradorContrato contrato = new GeradorContrato(projeto, cliente, );
        }
    }

    private static Orcamento validaProjetoGeraOrcamento(Catalogo catalogo, GerenciadorProjeto gerenciadorProjeto, Projeto projeto) {
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
