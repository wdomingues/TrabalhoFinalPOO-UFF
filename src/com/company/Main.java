package com.company;

import com.company.domain.Catalogo;
import com.company.domain.Cliente;
import com.company.domain.Orcamento;
import com.company.domain.Projeto;
import com.company.exceptions.DocumentoInvalidoException;
import com.company.exceptions.ObjetoNaoEncontradoException;
import com.company.helper.SituacaoProjeto;
import com.company.service.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
/*
Avaliação individual
2.a) Para persistir em bancos de dados, em mais de 1 SGBD:

o que será persistido?
	Os conteúdos que também são guardados nos arquivos chamados de mocks, os quais contém  separadamente:
		-Clientes
		-Fornecedores
		-Funcionários
		-Orçamentos
		-Projetos

- TIPO adequado de persistência
	Pela maneira como foi modelado o sistema, com geração de dados em Listas e Maps (Dicionários), em seguida convertidas
	em JSONs (persistência em arquivos), seria natural utilizar um banco de dados não relacional, noSQL, como o MongoDB,
	que persiste dados em forma de documentos, com suporte a JSON. Seria uma solução atual e objetiva.

	Também seria possível utilizar SGBDs relacionais, como o PostgreSQL. Para isso, seria necessário fazer a modelagem
	conceitual, identificando as entidades e relacionamentos, baseada no diagrama de classes (UML) deste projeto, a fim
	de fazer o projeto lógico no SGBD.

	Então, poderia ser, então feito o carregamento dos dados já existentes nos SGBDs.

	Em seguida, através da interface criada Persistencia, com a assinatura dos métodos de persistência em bancos de dados,
	podem ser implementadas classes de persistência para cada SGBD com pelo menos os métodos CRUD criados na interface,
	sendo sobrescritos de acordo com as especificidades do SGBD.

	Em seguida, as instãncias de persistência, poderiam ser invocadas sequencialmente à persistência de arquivos previamente
	existente (dos JSONs), fazendo as devidas conexões com os SGBDs e executando as transações desejadas.

	b)Implementação
	Foram criadas as classes de permanencia que implementan a interface Persistencia em com.company.helper:
		-PersistenciaMongo
		-PersistenciaPostgres

	Após instância com criação de cada Conexão ao seu respectivo SGBD, os métodos tem que ser implementados a fim de
	realizar as operações CRUD especificadas.
		Por exemplo, no caso do Postgres, como é relacional, o trabalho será maior, pois tem que ser selecionado atributo
		por atributo de cada registro, ou validar um registro completo, tendo tratamento.
	Cada método de interesse seria invocado nos classes Gerenciadoras, para inserir (salvar) paralelamente aos arquivos
	JSON, por exemplo.
	Já onde houver consultas, os métodos de busca ou listagem são invocados.
	A mesma analogia vai para a deleção e atualização.
*/

    public static void main(String[] args){
        boolean sair = false;
        String opcaoSelecionada, input = new String();
        Scanner scanner = new Scanner(System.in);
        GerenciadorCatalogo gerenciadorCatalogo = new GerenciadorCatalogo();
        Catalogo catalogo = null;
        try {
            catalogo = gerenciadorCatalogo.gerarCatalogo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
                            } catch (DocumentoInvalidoException exc){ //Uso da exception criada para validar documentos
                                System.out.println(exc.getMessage());
                            }
                            finally{
                                break;
                            }
                        }
                    }
                    catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    finally {
                        Projeto projeto = gerenciadorProjeto.cadastrar();
                        try{
                            projeto.setCliente(cliente);
                        } catch(ExceptionInInitializerError e){
                            System.out.println(e.getMessage());
                            projeto.setCliente(new Cliente());
                        }
                        finally {
                            try {
                                orcamento = validaProjetoGeraOrcamento(catalogo, gerenciadorProjeto, projeto);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }catch (ObjetoNaoEncontradoException o) {
                                System.out.println(o.getMessage());
                            } finally {
                                break;
                            }
                        }
                    }
                case "2":
                    try {
                        cliente = gerenciadorCliente.cadastrar();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    finally {
                        break;
                    }
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
                            //System.out.flush(); //aslam
                            imprimeOrcamento(orcamento);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        } catch (ObjetoNaoEncontradoException o){
                            System.out.println(o.getMessage());
                        }

                        finally{
                            break;
                        }
                    }

                case "":
                    sair = true;
                    break;
            }
            if (orcamento != null) {//TODO

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
        Scanner tec = new Scanner(System.in);
        System.out.println("Digite enter para continuar.");
        tec.nextLine();
    }

    private static Orcamento validaProjetoGeraOrcamento (Catalogo catalogo, GerenciadorProjeto gerenciadorProjeto, Projeto projeto)
            throws IOException, ObjetoNaoEncontradoException {
        Orcamento orcamento = null;
        if (ValidadorProjeto.validar(projeto, catalogo)) {
            try {
                orcamento = CalculadoraOrcamento.calcula(projeto);
            } catch(ObjetoNaoEncontradoException o){
                System.out.println(o.getMessage());
                return null;
            }
        } else {
            new Scanner(System.in).next();
            System.out.println("Vamos salvar o projeto para aguardar a atualização da lista de insumos através dos Fornecedores.");
            new Scanner(System.in).next();
            try{
                gerenciadorProjeto.salvaProjetoEmEspera(projeto);
            } catch(ObjetoNaoEncontradoException o){
                System.out.println(o.getMessage());
                return null;
            }
        }
        return orcamento;
    }

}
