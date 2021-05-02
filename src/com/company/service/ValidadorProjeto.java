package com.company.service;

import com.company.domain.CatalogoInsumo;
import com.company.domain.Projeto;

public class ValidadorProjeto {


    public static boolean validar(Projeto projeto, CatalogoInsumo catalogoInsumo) {
        //Verificar se existem insumos disponiveis para o projeto especifico
        projeto.getEdificacao().getAndares().forEach(andar -> andar.getComodos().forEach(comodo -> {

        }));

        return true;
    }
}
