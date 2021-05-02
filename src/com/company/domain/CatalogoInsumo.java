package com.company.domain;


import java.util.ArrayList;

public class CatalogoInsumo {
    private ArrayList<Insumo> insumos;

    public CatalogoInsumo() {

    }

    public CatalogoInsumo(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }
}
