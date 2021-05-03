package com.company.service;

import com.company.domain.CatalogoInsumo;
import com.company.domain.Fornecedor;
import com.company.domain.Insumo;
import com.company.domain.Projeto;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidadorProjeto {
    private static Map<java.lang.String, Map<java.lang.String, Double>> metrosQuadrados;


    public static boolean validar(Projeto projeto, CatalogoInsumo catalogoInsumo) {
        metrosQuadrados = geraMetrosQuadrados();

        Map<java.lang.String, Double> materiaisLaje = metrosQuadrados.get("Laje");
        Map<java.lang.String, Double> materiaisParede = metrosQuadrados.get("Parede");
        Map<java.lang.String, Double> materiaisColuna = metrosQuadrados.get("Coluna");

        Map<String, Double> insumosQuantidades = projeto.getInsumosNecessarios();
        Map<Insumo, Double> insumosNecessarios = insumosQuantidades.entrySet().stream().map(s -> new Insumo(s.getKey())).collect(Collectors.toMap(v-> v, v -> insumosQuantidades.get(v.getNome())));

        //Carrega a quantidade total de insumos necessarios para o projeto
        projeto.getEdificacao().forEach(ed -> ed.getAndares().forEach(andar ->
        {
            int qtdLajes = andar.getMetroQuadradoLajes();
            adicionaInsumosPorMaterial(materiaisLaje, insumosNecessarios, qtdLajes);

            int qtdParedes = andar.getMetroQuadradoParedes();
            adicionaInsumosPorMaterial(materiaisParede, insumosNecessarios, qtdParedes);

            int qtdColunas = andar.getColunas();
            adicionaInsumosPorMaterial(materiaisColuna, insumosNecessarios, qtdColunas);

        }));

        insumosNecessarios.forEach((insumo, qtdNecessaria) -> {
            Insumo insumoNoCatalogo = catalogoInsumo.getInsumos().stream().filter(insumo1 -> insumo1.getNome().equals(insumo.getNome())).findFirst().orElse(null);
            if (insumoNoCatalogo != null) {
                List<Fornecedor> fornecedoresDisponiveis = insumoNoCatalogo.getFornecedores().stream()
                        .filter(fornecedor -> fornecedor.getQuantidadeDisponivel() >= qtdNecessaria).collect(Collectors.toList());
                if (fornecedoresDisponiveis.stream().count() > 0)
                    fornecedoresDisponiveis.forEach(fornecedor -> {insumo.addFornecedor(fornecedor);});
                insumosQuantidades.put(insumoNoCatalogo.getNome(),qtdNecessaria);
                projeto.addInsumo(insumo);
            }
        });
        if (!insumosNecessarios.entrySet().stream().anyMatch(insumoN -> insumoN.getKey().getFornecedores() != null && insumoN.getKey().getFornecedores().stream().count() <= 0))
            projeto.setInsumosNecessarios(insumosQuantidades);
        else {
            var insumDetalhado = new Object() {
                java.lang.String insumosFaltantes = "";
            };
            insumosNecessarios.entrySet().stream()
                    .filter(insumoN -> insumoN.getKey().getFornecedores() != null && insumoN.getKey().getFornecedores().stream().count() <= 0)
                    .forEach(insumoFaltante -> {
                        insumDetalhado.insumosFaltantes =  insumDetalhado.insumosFaltantes + "\n" + insumoFaltante.getKey().getNome() + "\t\t\t\t" + insumoFaltante.getValue();

                    });
            System.out.println("\nNão há insumos suficientes para orçar o Projeto, \n" +
                    "entre em contato com fornecedores para atualizar a lista de insumos disponíveis.\nFaltam:");
            System.out.println(insumDetalhado.insumosFaltantes);
            return false;
        }


        return true;
    }

    private static void adicionaInsumosPorMaterial(Map<java.lang.String, Double> materiaisTipo, Map<Insumo, Double> listaDeInsumos, int qtdTipo) {
        materiaisTipo.forEach((nomeInsumo, quantidade) -> {
            if (listaDeInsumos.entrySet().stream().anyMatch(i -> i.getKey().getNome().equals(nomeInsumo)))
                listaDeInsumos.entrySet().stream()
                        .filter(insumoNecessario -> insumoNecessario.getKey().getNome().equals(nomeInsumo))
                        .forEach(insumoNecessario -> {
                            insumoNecessario.setValue(insumoNecessario.getValue() + quantidade * qtdTipo);
                        });
            else {
                listaDeInsumos.put(new Insumo(nomeInsumo), quantidade * qtdTipo);
            }
        });
    }

    private static Map<java.lang.String, Map<java.lang.String, Double>> geraMetrosQuadrados() {
        Map<java.lang.String, Map<java.lang.String, Double>> map = null;
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("./src/com/company/parameters/metrosQuadrados.json"));
            // convert JSON file to map
            map = gson.fromJson(reader, Map.class);
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }

    public Map<java.lang.String, Map<java.lang.String, Double>> getmetrosQuadrados() {
        if (metrosQuadrados == null)
            return geraMetrosQuadrados();
        return metrosQuadrados;
    }

    public void setmetrosQuadrados(Map<java.lang.String, Map<java.lang.String, Double>> metrosQuadrados) {
        this.metrosQuadrados = metrosQuadrados;
    }
}
