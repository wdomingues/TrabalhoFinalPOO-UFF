package com.company.domain;


public class Comodo {
    private int metroQuadradoLajes;
    private int metroQuadradoParedes;

    public int getMetroQuadradoLajes() {
        return metroQuadradoLajes;
    }

    public Comodo() {
    }

    public Comodo(int metroQuadradoLajes, int metroQuadradoParedes) {
        this.metroQuadradoLajes = metroQuadradoLajes;
        this.metroQuadradoParedes = metroQuadradoParedes;
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
}
