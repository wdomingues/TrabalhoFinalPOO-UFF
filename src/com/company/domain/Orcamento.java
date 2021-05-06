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
    private boolean tempoEmDias = false;

    private BigDecimal valorFinal, valorFixo, valorMovel;

    private int menorTempo, maiorTempo, maiorNumeroFuncionarios;

    public Orcamento(Projeto projeto) {
        setValorFixo(BigDecimal.valueOf(0));
        setValorMovel(BigDecimal.valueOf(0));
        setValorFinal(BigDecimal.valueOf(0));
        this.setNome(projeto.getNome());
        this.setEdificacao(projeto.getEdificacao());
        this.setFornecedores(projeto.getFornecedores());
        this.setInsumosNecessarios(projeto.getInsumosNecessarios());
    }

    @Override
    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        funcionarios.forEach(funcionario -> {
            setValorMovel(getValorMovel().add(funcionario.getSalarioHora()));
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
        return this.valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(BigDecimal valorFixo) {
        setValorFinal(valorFixo.add(getValorMovel()));
        this.valorFixo = valorFixo;
    }

    public BigDecimal getValorMovel() {
        if (this.valorMovel == null)
            return BigDecimal.valueOf(0);
        return this.valorMovel;
    }

    public void setValorMovel(BigDecimal valorMovel) {
        setValorFinal(valorMovel.add(getValorFixo()));
        this.valorMovel = valorMovel;
    }

    public Map<String, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<String, Integer> itens) {
        this.itens = itens;
    }

    public boolean isTempoEmDias() {
        return tempoEmDias;
    }

    public void setTempoEmDias(boolean tempoEmDias) {
        this.tempoEmDias = tempoEmDias;
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
}
