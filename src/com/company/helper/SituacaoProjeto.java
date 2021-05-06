package com.company.helper;

public enum SituacaoProjeto {
    AGUARDANDO(1, "Projeto aguardando liberação de insumos ou funcionários."),
    PENDENTE(2,"Projeto pendente de aprovação do orçamento para iniciar."),
    REVISAO(3,"Orçamento esta sendo revisado."),
    APROVADO(4,"Projeto iniciado."),
    FINALIZADO(5,"Projeto finalizado.");

    private int numero;
    private String ordinal;

    SituacaoProjeto(int s, String situacao) {
        this.numero = s;
        this.ordinal = situacao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
    }
}
