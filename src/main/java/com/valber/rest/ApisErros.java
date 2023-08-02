package com.valber.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApisErros {

    @Getter
    private List<String> erros;

    public ApisErros(String mensagemErro){
        this.erros = Arrays.asList(mensagemErro);
    }

    public ApisErros(List<String> erros) {
        this.erros = erros;
    }
}
