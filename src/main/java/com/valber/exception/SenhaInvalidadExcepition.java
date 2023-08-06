package com.valber.exception;

public class SenhaInvalidadExcepition extends RuntimeException {

    public SenhaInvalidadExcepition(){
        super("Senha invalida");
    }
}
