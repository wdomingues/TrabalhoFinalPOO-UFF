package com.company.helper;

public enum Enum_mocks {
    CLIENTE("mock-clientes"),
    PENDENTE("mock-fornecedores"),
    REVISAO("mock-funcionarios"),
    PROJETO("mock-projetos");

    private String nomeArquivo;
    Enum_mocks(String pathFile) {
        this.nomeArquivo = pathFile;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
