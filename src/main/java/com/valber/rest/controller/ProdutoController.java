package com.valber.rest.controller;

import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.Produto;
import com.valber.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @GetMapping("{id}")
    //@ResponseBody
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtos
                .findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos.findById(id)
                .map(produto -> {produtos.delete(produto);
                return produtos;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
          @PathVariable Integer id,
          @RequestBody @Valid  Produto produto){

        produtos.findById(id)
                .map( produtoExistente -> {
                    produto.setId( produtoExistente.getId());
                    produtos.save(produto);
                    return produtoExistente;
                }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Produto> lista = produtos.findAll(example);
        return produtos.findAll(example);
    }

}
