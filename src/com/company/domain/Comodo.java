package com.company.domain;


import java.util.ArrayList;

public class Comodo {
    private int metroQuadradoLajes;
    private int metroQuadradoParedes;
    private String nome;
    private ArrayList<Parede> paredes;
    private int qtdParedes;
    private Parede laje;

    public Comodo() {
    }

    public Comodo(int qtdParedes) {
        this.qtdParedes = qtdParedes;
    }

    //TODO: reavaliar
    public Comodo(int metroQuadradoLajes, int metroQuadradoParedes) {
        this.metroQuadradoLajes = metroQuadradoLajes;
        this.metroQuadradoParedes = metroQuadradoParedes;
    }

    //TODO: rever tipo da Laje
    public Parede criaLaje(){ //Para retangulares
        ArrayList<Float> larguras = new ArrayList();

        for (Parede p : paredes){
            larguras.add(p.getLargura());
        }
        //TODO: melhorar calculo para generalizar
        if (paredes.get(0).getLargura()==paredes.get(2).getLargura() && paredes.get(1).getLargura()==paredes.get(3).getLargura()){
            laje = new Parede(paredes.get(0).getLargura(),paredes.get(1).getLargura());
        }
        return laje;
    }

    public void setMetroQuadradoLajes(int metroQuadradoLajes) {
        this.metroQuadradoLajes = metroQuadradoLajes;
    }

    public int getMetroQuadradoParedes() {
        return metroQuadradoParedes;
    }

    public void setMetroQuadradoParedes(int metroQuadradoParedes) {
        this.metroQuadradoParedes = metroQuadradoParedes;
    }

    public void setNomeComodo(String nomeComodo) {
        this.nome = nomeComodo;
    }
    public String getNomeComodo() {
        return this.nome;
    }

    public void addParede(Parede parede) {
        this.paredes.add(parede);
    }
}
