package com.company.service;

import com.company.domain.Edificacao;
import com.company.domain.Orcamento;
import com.company.domain.Projeto;
import com.company.helper.SituacaoProjeto;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GerenciadorProjeto {
    private Projeto projeto;

    public static Projeto[] recuperaProjetos() {
        Projeto[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // cria projeto list
            ArrayList<Projeto> projetoList = new ArrayList<Projeto>();
            Reader reader = Files.newBufferedReader(Paths.get("./mock-projetos.json"));
            map = gson.fromJson(reader, Projeto[].class);

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

    public Projeto cadastrar() {
        Scanner scanner = new Scanner(System.in);
        String resposta = "s";
        this.projeto = new Projeto();
        GerenciadorEdificacao gerenciadorEdificacao = new GerenciadorEdificacao();
        Edificacao edificacao = gerenciadorEdificacao.projetarEdificacao();
        this.projeto.addEdificacao(edificacao);

        while (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim")) {
            System.out.println("\nAlguma outra edificação(" + Helpers.getOrdinal(edificacao.getOrdem() + 1, false) + ")? (S/N)");
            resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim")) {
                System.out.println("\nAs medidas da " + Helpers.getOrdinal(edificacao.getOrdem() + 1, false) + " edificação serão iguais a anterior? (S/N)");
                resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("sim"))
                    this.projeto.addEdificacao(gerenciadorEdificacao.projetarEdificacao(edificacao));
                else {
                    edificacao = gerenciadorEdificacao.projetarEdificacao();
                    this.projeto.addEdificacao(edificacao);
                    resposta = "S";
                }
            }
        }

        return this.projeto;
    }

    public Orcamento avancaProjetoOrcamento(Orcamento orcamento) {
        Helpers.clear();
        System.out.println("Orçamento do projeto: \t" + orcamento.getNome());
        if (orcamento.isTempoEmDias())
            System.out.println("Menor Prazo (em dias): \t" + orcamento.getMenorTempo());
        else
            System.out.println("Menor Prazo (em horas): " + orcamento.getMenorTempo());
        System.out.println("Insumos:");
        orcamento.getFornecedores().forEach(fornecedor -> {
            String fornecedorInsumo = fornecedor.getInsumo();
            var contadorCaracteres = fornecedorInsumo.length();
            var contadorCaracteres_qtdItens = orcamento.getItens().get(fornecedor.getInsumo()).toString().length();
            System.out.println("* - " + fornecedor.getInsumo() + (contadorCaracteres > 7 ? "\t\t" : "\t\t\t\t") +
                    orcamento.getItens().get(fornecedor.getInsumo()) +  (contadorCaracteres_qtdItens > 3 ? "\t" : "\t\t")  +
                    Helpers.monetarioBigDecimal(fornecedor.getValorUnitario()) + "\t" +
                    Helpers.monetarioBigDecimal(fornecedor.getValorUnitario().multiply(BigDecimal.valueOf(orcamento.getItens().get(fornecedor.getInsumo()))))
            );
        });
        System.out.println("Valor total dos insumos: \t\t\t" + Helpers.monetarioBigDecimal(orcamento.getValorFixo()));
//        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Mão de obra (" + orcamento.getMaiorNumeroFuncionarios() + " funcionários): \t\t\t\t\t\t" + Helpers.monetarioBigDecimal(orcamento.getValorMovel()));
        System.out.println("Preço final: \t\t\t\t\t\t" + Helpers.monetarioBigDecimal(orcamento.getValorFinal()));
        System.out.println("O Cliente aprovou esse orçamento? (S/N)");

        return null;
    }

    public Projeto selecionarProjeto(Projeto[] projetos, SituacaoProjeto[] situacoes) {
        int aux = 1;
        Helpers.clear();
        System.out.println("\nSelecione o projeto para avançar situação ou deletar: ");
        if (situacoes.length == 1 || situacoes.length == 0) {
            for (Projeto proj : projetos) {
                System.out.println(aux + " - " + proj.getNome());
                aux++;
            }
        } else {
            for (SituacaoProjeto situacao : situacoes) {
                Projeto[] projs = Arrays.stream(projetos).filter(p -> p.getSituacao().equals(situacao)).toArray(Projeto[]::new);
                if (projs != null && projs.length > 0) {
                    System.out.println("\n\n" + (situacao.getOrdinal()));
                    for (Projeto proj : projs) {
                        System.out.println(aux + " - " + proj.getNome());
                        aux++;
                    }
                }
            }
        }
        int res = Helpers.validaInteiroPositivo();
        System.out.println("\n");
        if (res <= projetos.length && res > 0)
            this.projeto = projetos[res - 1];
        else
            return selecionarProjeto(projetos, situacoes);


        return this.projeto;
    }

    public Projeto[] salvaProjetoEmEspera(Projeto projeto) {
        return salvaProjeto(projeto, SituacaoProjeto.AGUARDANDO);
    }

    public Projeto[] salvaProjetoPendenteAprovacao(Projeto projeto) {
        return salvaProjeto(projeto, SituacaoProjeto.PENDENTE);
    }

    public Projeto[] salvaProjetoOrcamentoRevisao(Projeto projeto) {
        return salvaProjeto(projeto, SituacaoProjeto.REVISAO);
    }

    public Projeto[] salvaProjetoAprovado(Projeto projeto) {
        return salvaProjeto(projeto, SituacaoProjeto.APROVADO);
    }

    public Projeto[] finalizaProjeto(Projeto projeto) {
        return salvaProjeto(projeto, SituacaoProjeto.FINALIZADO);
    }


    private Projeto[] salvaProjeto(Projeto projeto, SituacaoProjeto situacao) {
        Projeto[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            projeto.setSituacao(situacao);
            // cria projeto list
            ArrayList<Projeto> projetoList = new ArrayList<Projeto>();
            projetoList.add(projeto);
            Reader reader = Files.newBufferedReader(Paths.get("./mock-projetos.json"));
            map = gson.fromJson(reader, Projeto[].class);
            reader.close();

            if (map != null && map.length > 0)
                Arrays.stream(map).forEach(p1 -> {
                    if (!p1.getNome().equals(projeto.getNome()))
                        projetoList.add(p1);
                });
            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("./mock-projetos.json"));

            for (Projeto projeto1 : projetoList) {
                if (projeto1.getNome() == null || projeto1.getNome().isEmpty())
                    projeto1.setNome("Projeto_" + (projetoList.indexOf(projeto1) + 1));
            }
            // convert Projetos object to JSON file
            gson.toJson(projetoList, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }
}
