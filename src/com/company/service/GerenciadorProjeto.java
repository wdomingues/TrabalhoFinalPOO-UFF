package com.company.service;

import com.company.domain.Edificacao;
import com.company.domain.Projeto;
import com.company.helper.SituacaoProjeto;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;
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


        //TODO: pensar em deslocar para Gerenciador de projeto
        //System.out.println("Digite o prazo máximo que você deseja:");
        //this.edificacao.setPrazoDias(scanner.nextInt());

        //TODO: calcular baseado em prazo, consid dias de trab x nFuncionarios para exec comodo


        return this.projeto;
    }

    public Projeto selecionarProjeto(Projeto[] projetos, SituacaoProjeto[] situacoes) {
        int aux = 1;
        Helpers.clear();
        System.out.println("\nSelecione o projeto para avançar situação ou deletar: ");
        if (situacoes.length == 1) {
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
        if (res <= projetos.length)
            this.projeto = projetos[res - 1];
        else
            return selecionarProjeto(projetos,situacoes);


        return this.projeto;
    }

    public Projeto[] salvaProjetoEmEspera(Projeto projeto) {
        Projeto[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            projeto.setSituacao(SituacaoProjeto.AGUARDANDO);
            // cria projeto list
            ArrayList<Projeto> projetoList = new ArrayList<Projeto>();
            projetoList.add(projeto);
            Reader reader = Files.newBufferedReader(Paths.get("./mock-projetos.json"));
            map = gson.fromJson(reader, Projeto[].class);
            reader.close();

            if (map != null && map.length > 0)
                Arrays.stream(map).forEach(p1 -> {
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
