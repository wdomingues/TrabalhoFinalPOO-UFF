package com.company.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Orcamento extends Projeto {
    private Map<String, Integer> itens = new HashMap<>();
    private Map<String, Integer> menorTempoDiasPorEdificacao = new HashMap<>();
    private Map<String, Integer> maiorTempoDiasPorEdificacao = new HashMap<>();
    private Map<String, Integer> maxFuncionarioPorEdificacao = new HashMap<>();

//    private Map<String,Double> itensJSON = new HashMap<>();
//    private Map<String,Double> menorTempoDiasPorEdificacaoJSON = new HashMap<>();
//    private Map<String,Double> maiorTempoDiasPorEdificacaoJSON = new HashMap<>();
//    private Map<String,Double> maxFuncionarioPorEdificacaoJSON = new HashMap<>();

    private BigDecimal valorFinal, valorFixo, valorMovel;

    private int menorTempo, maiorTempo, maiorNumeroFuncionarios;
    private String nomeProjeto; //aslam

    public Orcamento(Projeto projeto) {
        setValorFixo(BigDecimal.valueOf(0));
        setValorMovel(BigDecimal.valueOf(0));
        setValorFinal(BigDecimal.valueOf(0));
        this.setEdificacao(projeto.getEdificacao());
        this.setFornecedores(projeto.getFornecedores());
        this.setInsumosNecessarios(projeto.getInsumosNecessarios());
        this.nomeProjeto = projeto.getNome();
    }

    @Override
    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        funcionarios.forEach(funcionario -> {
            this.valorMovel = this.valorMovel.add(funcionario.getSalarioHora());
        });
        super.setFuncionarios(funcionarios);
    }

    public Map<String, Integer> getMenorTempoDiasPorEdificacao() {
        return menorTempoDiasPorEdificacao;
    }

    public void setMenorTempoDiasPorEdificacao(Map<String, Integer> menorTempoDiasPorEdificacao) {
        this.menorTempoDiasPorEdificacao = menorTempoDiasPorEdificacao;
    }

    public Map<String, Integer> getMaiorTempoDiasPorEdificacao() {
        return maiorTempoDiasPorEdificacao;
    }

    public void setMaiorTempoDiasPorEdificacao(Map<String, Integer> maiorTempoDiasPorEdificacao) {
        this.maiorTempoDiasPorEdificacao = maiorTempoDiasPorEdificacao;
    }

    public Map<String, Integer> getMaxFuncionarioPorEdificacao() {
        return maxFuncionarioPorEdificacao;
    }

    public void setMaxFuncionarioPorEdificacao(Map<String, Integer> maxFuncionarioPorEdificacao) {
        this.maxFuncionarioPorEdificacao = maxFuncionarioPorEdificacao;
    }

    public int getMenorTempo() {
        return menorTempo;
    }

    public void setMenorTempo(int menorTempo) {
        this.menorTempo = menorTempo;
    }

    public int getMaiorTempo() {
        return maiorTempo;
    }

    public void setMaiorTempo(int maiorTempo) {
        this.maiorTempo = maiorTempo;
    }

    public int getMaiorNumeroFuncionarios() {
        return maiorNumeroFuncionarios;
    }

    public void setMaiorNumeroFuncionarios(int maiorNumeroFuncionarios) {
        this.maiorNumeroFuncionarios = maiorNumeroFuncionarios;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(BigDecimal valorFixo) {
        this.valorFixo = valorFixo;
    }

    public BigDecimal getValorMovel() {
        return valorMovel;
    }

    public void setValorMovel(BigDecimal valorMovel) {
        this.valorMovel = valorMovel;
    }

    public Map<String, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<String, Integer> itens) {
        this.itens = itens;
    }


    public Map<String, Integer> addItens(Insumo insumo, int quantidade) {
        this.setValorFixo(this.getValorFixo().add(insumo.getFornecedores().get(0).getValorUnitario().multiply(BigDecimal.valueOf(quantidade))));
        this.itens.put(insumo.getNome(), quantidade);
        return this.itens;
    }

    public Map<String, Integer> addMenorTempoDiasEdificacao(Edificacao edificacao, int menorTempo) {
        this.menorTempoDiasPorEdificacao.put(String.valueOf(edificacao.getOrdem()), menorTempo);
        return this.menorTempoDiasPorEdificacao;
    }

    public Map<String, Integer> addmaxFuncionarioEdificacao(Edificacao edificacao, int maxFunc) {
        this.maxFuncionarioPorEdificacao.put(String.valueOf(edificacao.getOrdem()), maxFunc);
        return this.maxFuncionarioPorEdificacao;
    }

    public Map<String, Integer> addMaiorTempoDiasEdificacao(Edificacao edificacao, int maiorTempo) {
        this.maiorTempoDiasPorEdificacao.put(String.valueOf(edificacao.getOrdem()), maiorTempo);
        return this.maiorTempoDiasPorEdificacao;
    }

    public String getNomeProjeto() {//aslam
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {//aslam
        this.nomeProjeto = nomeProjeto;
    }
}
