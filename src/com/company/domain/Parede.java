package com.company.domain;


public class Parede {
    private float largura;
    private float altura;
    private int numero;

    public Parede() {
    }

    public Parede(float largura, float altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public Parede(float largura) { //Se parede quadrada: h=l
        this.largura = largura;
        this.altura = largura;
    }

    public float getArea() {
        return largura * altura;
    }

    public float getLargura() {
        return largura;
    }
    public float getAltura() {
        return altura;
    }

    public void setDimensoes(float largura, float altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

}
