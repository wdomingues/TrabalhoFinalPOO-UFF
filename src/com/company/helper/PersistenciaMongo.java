package com.company.helper;

import com.company.domain.Projeto;

public class PersistenciaMongo implements Persistencia{

    @Override
    public Projeto selecionar(String projeto) {
        return null;
    }

    @Override
    public void inserir(Projeto p) {

    }

    @Override
    public void remover(String projeto) {

    }

    @Override
    public void atualizar(String projeto) {

    }

    @Override
    public Projeto[] listar(String projeto) {
        return new Projeto[0];
    }
}
