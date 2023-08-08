package com.valber.rest.controller;

import com.valber.domain.entity.Cliente;
import com.valber.domain.repository.Clientes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@Controller
//@RequestMapping("api")
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

//    @RequestMapping(
//            value = "/hello/{nome}",
//            method = RequestMethod.GET
//           )
    //consumes = {" application/json","application/xml"}
    //produces = {" application/json","application/xml"}

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    @ApiOperation("Ober detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message=  "Cliente encontrado"),
            @ApiResponse(code = 404, message=  "Cliente não encontrado")
    })
    //@ResponseBody
    public Cliente getClienteById(@PathVariable Integer id) {
        //Optional<Cliente> cliente = clientes.findById(id);
        return clientes
                .findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"));
    }


    //@ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save (@RequestBody @Valid Cliente cliente){
        //Cliente clienteSalvo = clientes.save(cliente);
        //return ResponseEntity.ok(clienteSalvo);
        return clientes.save(cliente);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){
//        Optional<Cliente> cliente = clientes.findById(id);
//        if (cliente.isPresent()) {
//            clientes.delete(cliente.get());
//            return ResponseEntity.noContent().build();
//        }
//        return  ResponseEntity.noContent().build();
        clientes.findById(id)
                .map(cliente -> {clientes.delete(cliente);
                return clientes;
                 })
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable Integer  id,
            @RequestBody @Valid Cliente cliente){

         clientes
                       .findById(id)
                       .map( clientExistente -> {
                           cliente.setId(clientExistente.getId());
                           clientes.save(cliente);
                           return clientExistente;
                       }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                 "Cliente nao encontrado"));

    }


    @GetMapping
    public List<Cliente> find(Cliente filtro){

       ExampleMatcher matcher = ExampleMatcher
               .matching()
               .withIgnoreCase()
               .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

               Example example = Example.of(filtro, matcher);
               List<Cliente> lista = clientes.findAll(example);
               return clientes.findAll(example);
    }
}
