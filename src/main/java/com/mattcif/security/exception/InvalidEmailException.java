package com.mattcif.security.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
        super("Email inválido");
    }
}
