package com.valber;


import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.Pedido;
import com.valber.domain.repository.Clientes;
import com.valber.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos) {
        return args -> {

            System.out.println("Inserting clients");
            clientes.save(new Cliente("Miguel"));
            clientes.save(new Cliente("Vladmir"));
            Cliente man = new Cliente("Alfred");

            Pedido p = new Pedido();
            p.setCliente(man);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            Cliente cli = clientes.findClienteFetchPedidos(man.getId());
            System.out.println(cli);
            System.out.printf(String.valueOf(cli.getPedidos()));

//            clientes.salvar(new Cliente("Miguel"));
//            clientes.salvar(new Cliente("Vladmir"));

//            List<Cliente> todosClientes = clientes.obterTodos();
//            List<Cliente> todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//           todosClientes.forEach(c -> {
//               c.setNome(c.getNome() + " atualizado");
//               clientes.save(c);
//           });
//
//            System.out.println("We found vlad - " +clientes.findNomeByLike("Vlad"));
            //clientes.findNomeByLike("Vla").forEach(System.out::println);
            //System.out.println("FOUND - " + clientes.findById(2));
//
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Deleting clients");
////            clientes.obterTodos().forEach(c -> {
////                clientes.deletar(c);
////            });
//
             //clientes.delete(new Cliente(1));
           // clientes.deleteByNome("Miguel");
//
//            todosClientes = clientes.findAll();
//
//            if(todosClientes.isEmpty()){
//                System.out.println("No clients found");
//            }else{
//                todosClientes = clientes.findAll();
//                todosClientes.forEach(System.out::println);
//            }
        };
    }

    public static void main (String[]args){
        SpringApplication.run(VendasApplication.class, args);
    }
}
