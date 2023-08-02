package com.valber.rest.controller;

import com.valber.exception.PedidoNaoEncontradoException;
import com.valber.exception.RegraNegocioException;
import com.valber.rest.ApisErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApisErros handleRegraNegocioException(RegraNegocioException ex){
        String menssageErro = ex.getMessage();
        return new ApisErros(menssageErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApisErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApisErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApisErros handleMethodnotValidExcetion(MethodArgumentNotValidException ex){
       List<String> erros =  ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro ->  erro.getDefaultMessage())
                .collect(Collectors.toList());

       return new ApisErros(erros);
    }

}
