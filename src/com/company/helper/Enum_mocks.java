package com.company.helper;

import com.company.domain.Cliente;
import com.company.domain.Fornecedor;
import com.company.domain.Funcionario;
import com.company.domain.Projeto;

public enum Enum_mocks {
    CLIENTE("mock-clientes", Cliente[].class),
    PENDENTE("mock-fornecedores", Fornecedor[].class),
    REVISAO("mock-funcionarios", Funcionario[].class),
    PROJETO("mock-projetos",Projeto[].class);

    private Class classe;
    private String nomeArquivo;
    Enum_mocks(String pathFile, Class classe) {
        this.nomeArquivo = pathFile;
        this.classe = classe;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
