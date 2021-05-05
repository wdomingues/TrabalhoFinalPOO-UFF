package com.company.service;

import com.company.domain.*;
import com.company.helper.SituacaoProjeto;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CalculadoraOrcamento {
    private static Map<String, Double> funcionarioProduzEmHoras;
    private static Map<String, Double> funcionariosProduzirEmUmaHora;

    public static Orcamento calcula(Projeto projeto) {
        Orcamento orcamento = new Orcamento(projeto);
        funcionarioProduzEmHoras = getFuncionarioProduzEmHoras();
        funcionariosProduzirEmUmaHora = getFuncionariosProduzirEmUmaHora();
        calculaAndarPorEdificacao(orcamento);

        var funcionarios = new GerenciadorCatalogo().identificarFuncionariosDisponiveis();

        // A quantidade máxima de funcionarios: Se todas as edificações são construidas ao mesmo tempo
        orcamento.getMaxFuncionarioPorEdificacao().forEach((edificacao, integer) -> {
            orcamento.setMaiorNumeroFuncionarios(orcamento.getMaiorNumeroFuncionarios() + integer);
        });
        if (orcamento.getMaiorNumeroFuncionarios() > funcionarios.length)
            orcamento.setMaiorNumeroFuncionarios(funcionarios.length);

        // O maior tempo que o projeto pode demorar, com apenas um funcionário
        orcamento.getMaiorTempoDiasPorEdificacao().forEach((edificacao, integer) -> {
            orcamento.setMaiorTempo(orcamento.getMaiorTempo() + integer);
        });

        // O menor tempo possível para obra ocorrer: todas as edificações são construidas ao mesmo tempo
        // (A edificação que demorar mais será o menor tempo possivel para o fim do projeto
        orcamento.setMenorTempo(orcamento.getMenorTempoDiasPorEdificacao().entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).findFirst().orElse(null).getValue());


        Map<String, Double> insumosQuantidades = projeto.getInsumosNecessarios();
        Map<Insumo, Double> insumosNecessarios = insumosQuantidades.entrySet().stream().map(s -> new Insumo(s.getKey(),
                projeto.getInsumos().stream().filter(i -> i.getNome().equals(s.getKey()))
                        .findFirst().get().getFornecedores().stream().collect(Collectors.toCollection(ArrayList::new))))
                .collect(Collectors.toMap(v -> v, v -> insumosQuantidades.get(v.getNome())));

        insumosNecessarios.forEach((insumo, quantidade) -> {
            BigDecimal valorInsumoProjeto;
            List<Fornecedor> fornecedores = insumo.getFornecedores().stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());

            Fornecedor fornecedor = fornecedores.stream().findFirst().orElse(null);
            if (fornecedor != null)
                if (quantidade < 1) {
                    adicionaItensNoOrcamento(orcamento, insumo, fornecedor, 1);
                } else {
                    int qtd = (int) Math.ceil(quantidade);
                    adicionaItensNoOrcamento(orcamento, insumo, fornecedor, qtd);
                }
            else {
                System.out.println("Existem insumos sem fornecedores nesse orçamento.");

            }
            orcamento.addFornecedor(fornecedor);
        });

        var funcionariosSelecionados = Arrays.stream(Helpers.getSliceOfArray(funcionarios, 0, orcamento.getMaiorNumeroFuncionarios())).toArray(Funcionario[]::new);
        orcamento.setFuncionarios((ArrayList<Funcionario>) Arrays.stream(funcionariosSelecionados).collect(Collectors.toList()));
        orcamento.setValorMovel(orcamento.getValorMovel().multiply(BigDecimal.valueOf(orcamento.getMenorTempo())));
        salvaOrcamentoPendenteAprovacao(orcamento);
        new GerenciadorProjeto().salvaProjetoPendenteAprovacao(orcamento);
        return orcamento;
    }

    private static void adicionaItensNoOrcamento(Orcamento orcamento, Insumo insumo, Fornecedor fornecedor, int qtd) {
        Insumo ins = new Insumo(insumo.getNome());
        ins.addFornecedor(fornecedor);
        orcamento.addItens(ins, qtd);
    }

    private static void calculaAndarPorEdificacao(Orcamento orcamento) {
        int tempoLajeProduzidaPorUmFuncionario = (int) Math.ceil(funcionarioProduzEmHoras.get("Laje"));
        int funcionariosParaProduzirEmUmaHora_Laje = (int) Math.ceil(funcionariosProduzirEmUmaHora.get("Laje"));

        int tempoParedeProduzidaPorUmFuncionario = (int) Math.ceil(funcionarioProduzEmHoras.get("Parede"));
        int funcionariosParaProduzirEmUmaHora_Parede = (int) Math.ceil(funcionariosProduzirEmUmaHora.get("Parede"));

        int tempoColunaProduzidaPorUmFuncionario = (int) Math.ceil(funcionarioProduzEmHoras.get("Coluna"));
        int funcionariosParaProduzirEmUmaHora_Coluna = (int) Math.ceil(funcionariosProduzirEmUmaHora.get("Coluna"));

        int maiorNumeroFuncionarios = Math.max(funcionariosParaProduzirEmUmaHora_Laje, Math.max(funcionariosParaProduzirEmUmaHora_Parede, funcionariosParaProduzirEmUmaHora_Coluna));

        orcamento.getEdificacao().forEach(ed -> {
            orcamento.setMenorTempo(0);
            orcamento.setMaiorNumeroFuncionarios(0);
            ed.getAndares().forEach(andar ->
            {
                int qtdLajes = andar.getMetroQuadradoLajes();
                int todasLajesPorUmFuncionario = tempoLajeProduzidaPorUmFuncionario * qtdLajes;

                int qtdParedes = andar.getMetroQuadradoParedes();
                int todasParedesPorUmFuncionario = tempoParedeProduzidaPorUmFuncionario * qtdParedes;

                int qtdColunas = andar.getColunas();
                int todasColunasPorUmFuncionario = tempoColunaProduzidaPorUmFuncionario * qtdColunas;

                int tudoPorUmFuncionario = todasLajesPorUmFuncionario + todasParedesPorUmFuncionario + todasColunasPorUmFuncionario;

                int menorNumeroProdutoAndar = Math.min(qtdLajes, Math.min(qtdParedes, qtdColunas));
                int maiorNumeroFuncionariosPorAndar = menorNumeroProdutoAndar * maiorNumeroFuncionarios;
                int menorTempoPossivel = (qtdColunas + qtdLajes + qtdParedes);

                orcamento.setMenorTempo(orcamento.getMenorTempo() + menorTempoPossivel);
                orcamento.setMaiorNumeroFuncionarios(orcamento.getMaiorNumeroFuncionarios() + maiorNumeroFuncionariosPorAndar);
                orcamento.setMaiorTempo(orcamento.getMaiorTempo() + tudoPorUmFuncionario);
            });
            orcamento.addMenorTempoDiasEdificacao(ed, Helpers.minimoDiasPorHora(orcamento.getMenorTempo()));
            orcamento.addMaiorTempoDiasEdificacao(ed, Helpers.minimoDiasPorHora(orcamento.getMaiorTempo()));
            orcamento.addmaxFuncionarioEdificacao(ed, orcamento.getMaiorNumeroFuncionarios());
        });

        // Reseta os valores que estavam sendo usados como auxiliadores para calcular o tempo por edificação
        orcamento.setMaiorNumeroFuncionarios(0);
        orcamento.setMenorTempo(0);
        orcamento.setMaiorTempo(0);
    }

    // Exibe quantas HORAS um funcionário gasta para produzir cada item
    public static Map<String, Double> getFuncionarioProduzEmHoras() {
        if (funcionarioProduzEmHoras == null)
            return geraFuncionarioTempo();
        return funcionarioProduzEmHoras;
    }

    // Exibe quantos FUNCIONÁRIOS precisam para produzir cada item em uma hora
    public static Map<String, Double> getFuncionariosProduzirEmUmaHora() {
        if (funcionariosProduzirEmUmaHora == null)
            return gerarTempoFuncionarios();
        return funcionariosProduzirEmUmaHora;
    }

    //Carrega o arquivo da relação de produção: tempo (horas) para um produto por funcionário
    private static Map<String, Double> geraFuncionarioTempo() {
        return getFileMapStringDouble("./src/com/company/parameters/funcionarioTempo.json");
    }

    //Carrega o arquivo da relaçao de produção: funcinários para um produto em uma hora
    private static Map<String, Double> gerarTempoFuncionarios() {
        return getFileMapStringDouble("./src/com/company/parameters/tempoFuncionarios.json");
    }

    private static Map<String, Double> getFileMapStringDouble(String path) {
        Map<String, Double> map = null;
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(path));
            // convert JSON file to map
            map = gson.fromJson(reader, Map.class);
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }


    public static Projeto[] salvaOrcamentoPendenteAprovacao(Orcamento orcamento) {
        return salvaOrcamento(orcamento, SituacaoProjeto.PENDENTE);
    }

    public static Projeto[] salvaOrcamentoOrcamentoRevisao(Orcamento orcamento) {
        return salvaOrcamento(orcamento, SituacaoProjeto.REVISAO);
    }

    public static Projeto[] salvaOrcamentoAprovado(Orcamento orcamento) {
        return salvaOrcamento(orcamento, SituacaoProjeto.APROVADO);
    }


    private static Orcamento[] salvaOrcamento(Orcamento orcamento, SituacaoProjeto situacao) {
        Orcamento[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();
            // cria Orcamento list
            ArrayList<Orcamento> OrcamentoList = new ArrayList<Orcamento>();

            orcamento.setSituacao(situacao);
            OrcamentoList.add(orcamento);
            Reader reader = Files.newBufferedReader(Paths.get("./mock-Orcamentos.json"));
            map = gson.fromJson(reader, Orcamento[].class);
            reader.close();

            if (map != null && map.length > 0)
                Arrays.stream(map).forEach(p1 -> {
                    if (p1.getNome() != null && !p1.getNome().isEmpty() && !p1.getNome().isBlank())
                        if (!p1.getNome().equals(orcamento.getNome()))
                            OrcamentoList.add(p1);
                        //else

                });
            //Arrays.stream(map).reduce()
            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("./mock-orcamentos.json"));
            // convert Orcamentos object to JSON file
            gson.toJson(OrcamentoList, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

    private static Orcamento usaOrcamento(String dadoBusca) {
        Orcamento[] map = null;
        try {
            // create Gson instance
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("./mock-orcamentos.json"));
            map = gson.fromJson(reader, Orcamento[].class);
            reader.close();
            Orcamento orcamento = Arrays.stream(map).filter(c -> c.getNome().equals(dadoBusca) || c.getValorFinal().equals(dadoBusca)).findFirst().orElse(null);
            return orcamento;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
