package com.company.domain;

public class Andar {
    private int metroQuadradoLajes;
    private int metroQuadradoParedes;
    private int colunas;

    public Andar(int metroQuadradoLajes, int metroQuadradoParedes, int colunas) {
        this.metroQuadradoLajes = metroQuadradoLajes;
        this.metroQuadradoParedes = metroQuadradoParedes;
        this.colunas = colunas;
    }

    public int getMetroQuadradoLajes() {
        return metroQuadradoLajes;
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

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }
}
