package com.company.exceptions;

public class CPFInvalidoException extends RuntimeException{
    public CPFInvalidoException(String message) {
        super(message);
    }
}
