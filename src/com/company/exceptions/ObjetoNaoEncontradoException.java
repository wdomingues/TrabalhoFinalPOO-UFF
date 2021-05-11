package com.company.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {
    //utilizado para lançar exceções em falhas de conversão de JSON para Map
    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
