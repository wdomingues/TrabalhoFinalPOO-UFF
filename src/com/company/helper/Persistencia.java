package com.company.helper;

import com.company.domain.Projeto;

public interface Persistencia { //interface comum as classes que implementam persistencia em banco de dados
                                //Inicialmente usada no Gerenciador de Projeto e no de Orcamento
    public Projeto selecionar (String projeto);
    public void inserir (Projeto p);
    public void remover (String projeto);
    public void atualizar (String projeto);
    public Projeto[] listar (String projeto);
}
