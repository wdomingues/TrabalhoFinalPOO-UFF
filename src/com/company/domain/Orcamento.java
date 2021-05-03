package com.company.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Orcamento extends Projeto {
    private Map<Insumo, Integer> itens = new HashMap<>();
    private Map<Edificacao, Integer> menorTempoDiasPorEdificacao = new HashMap<>();
    private Map<Edificacao, Integer> maiorTempoDiasPorEdificacao = new HashMap<>();
    private Map<Edificacao, Integer> maxFuncionarioPorEdificacao = new HashMap<>();


    private BigDecimal valorFinal, valorFixo, valorMovel;

    private int menorTempo, maiorTempo, maiorNumeroFuncionarios;

    public Orcamento(Projeto projeto) {
        setValorFixo(BigDecimal.valueOf(0));
        setValorMovel(BigDecimal.valueOf(0));
        setValorFinal(BigDecimal.valueOf(0));
        this.setEdificacao(projeto.getEdificacao());
        this.setFornecedores(projeto.getFornecedores());
        this.setInsumosNecessarios(projeto.getInsumosNecessarios());
    }

    @Override
    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        super.setFuncionarios(funcionarios);
        funcionarios.forEach(funcionario -> {
            this.valorMovel = this.valorMovel.add(funcionario.getSalarioHora());
        });
    }

    public Map<Edificacao, Integer> getMenorTempoDiasPorEdificacao() {
        return menorTempoDiasPorEdificacao;
    }

    public void setMenorTempoDiasPorEdificacao(Map<Edificacao, Integer> menorTempoDiasPorEdificacao) {
        this.menorTempoDiasPorEdificacao = menorTempoDiasPorEdificacao;
    }

    public Map<Edificacao, Integer> getMaiorTempoDiasPorEdificacao() {
        return maiorTempoDiasPorEdificacao;
    }

    public void setMaiorTempoDiasPorEdificacao(Map<Edificacao, Integer> maiorTempoDiasPorEdificacao) {
        this.maiorTempoDiasPorEdificacao = maiorTempoDiasPorEdificacao;
    }

    public Map<Edificacao, Integer> getMaxFuncionarioPorEdificacao() {
        return maxFuncionarioPorEdificacao;
    }

    public void setMaxFuncionarioPorEdificacao(Map<Edificacao, Integer> maxFuncionarioPorEdificacao) {
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

    public Map<Insumo, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<Insumo, Integer> itens) {
        this.itens = itens;
    }

    public Map<Insumo, Integer> addItens(Insumo insumo, int quantidade) {
        this.setValorFixo(this.getValorFixo().add(insumo.getFornecedores().get(0).getValorUnitario().multiply(BigDecimal.valueOf(quantidade))));
        this.itens.put(insumo, quantidade);
        return this.itens;
    }

    public Map<Edificacao, Integer> addMenorTempoDiasEdificacao(Edificacao edificacao, int menorTempo) {
        this.menorTempoDiasPorEdificacao.put(edificacao, menorTempo);
        return this.menorTempoDiasPorEdificacao;
    }

    public Map<Edificacao, Integer> addmaxFuncionarioEdificacao(Edificacao edificacao, int maxFunc) {
        this.maxFuncionarioPorEdificacao.put(edificacao, maxFunc);
        return this.maxFuncionarioPorEdificacao;
    }

    public Map<Edificacao, Integer> addMaiorTempoDiasEdificacao(Edificacao edificacao, int maiorTempo) {
        this.maiorTempoDiasPorEdificacao.put(edificacao, maiorTempo);
        return this.maiorTempoDiasPorEdificacao;
    }
}
