package com.valber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

//    @Bean
//    public CommandLineRunner init(
//            @Autowired Clientes clientes,
//            @Autowired Pedidos pedidos
//    ) {
//        return args ->  {
//             {
//
//                System.out.println("Inserting clients");
//                clientes.save(new Cliente("Miguel"));
//                clientes.save(new Cliente("Sol"));
//                clientes.save(new Cliente("Vladmir"));
//                Cliente man = new Cliente("Alfred");
//                 Cliente man2 = new Cliente("Jhon");
//                clientes.save(man);
//                clientes.save(man2);
//
//                Pedido p = new Pedido();
//                p.setCliente(man);
//                p.setDataPedido(LocalDate.now());
//                p.setTotal(BigDecimal.valueOf(100));
//
//                pedidos.save(p);
//
//                Cliente cliente = clientes.findClienteFetchPedidos(man.getId());
//                System.out.println(cliente);
//                System.out.println(cliente.getPedidos());
//
//                List<Pedido> x = null ;
//
//                if( pedidos.findByCliente(man2).size() > 0){
//                    System.out.println("No ask");
//                }else{
//                    System.out.println(pedidos.findByCliente(man2));
//                }
//
//                //System.out.println("Deleting clients");
//                //clientes.deleteByNomeQuery("Sol");
//
//                //List<Cliente> allClientes = clientes.findAll();
//                //allClientes.forEach(System.out::println);
//
////              System.out.println("We found vlad - " + clientes.findByNomeLike("mir"));
//
////            Pedido p = new Pedido();
////            p.setCliente(man);
////            p.setDataPedido(LocalDate.now());
////            p.setTotal(BigDecimal.valueOf(100));
//
//                //pedidos.save(p);
//
////            Cliente cli = clientes.findClienteFetchPedidos(man.getId());
//                //           System.out.println(cli);
////            System.out.println(cli.getPedidos());
////            clientes.salvar(new Cliente("Miguel"));
////            clientes.salvar(new Cliente("Vladmir"));
//
////            List<Cliente> todosClientes = clientes.obterTodos();
////            List<Cliente> todosClientes2 = clientes.findAll();
////            todosClientes2.forEach(System.out::println);
////
////           todosClientes.forEach(c -> {
////               c.setNome(c.getNome() + " atualizado");
////               clientes.save(c);
////           });
////
////            System.out.println("We found vlad - " +clientes.findNomeByLike("Vlad"));
//                //clientes.findNomeByLike("Vla").forEach(System.out::println);
//                //System.out.println("FOUND - " + clientes.findById(2));
////
////            todosClientes = clientes.findAll();
////            todosClientes.forEach(System.out::println);
////
////            System.out.println("Deleting clients");
//////            clientes.obterTodos().forEach(c -> {
//////                clientes.deletar(c);
//////            });
////
//                //clientes.delete(new Cliente(1));
////           clientes.deleteByNome("Sol");
////
////            todosClientes = clientes.findAll();
////
////            if(todosClientes.isEmpty()){
////                System.out.println("No clients found");
////            }else{
////                todosClientes = clientes.findAll();
////                todosClientes.forEach(System.out::println);
////            }
//            }
//        };
//    }

//    @Bean
//    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
//        return args -> {
//            Cliente cli = new Cliente(null,"Morgam");
//            clientes.save(cli);
//
//        };
//    }

    public static void main (String[]args){
        SpringApplication.run(VendasApplication.class, args);
    }
}
