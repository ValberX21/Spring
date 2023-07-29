package com.valber.domain.repository;

import com.valber.domain.entity.Cliente;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = " select nome from cliente c where c.nome like %:nome% ", nativeQuery = true)
    String findByNomeLike(@Param("nome") String nome);

    @Transactional
    void deleteByNome(String nome);

    @Transactional
    @Modifying
    @Query(value = " delete from Cliente c where c.nome=:nome")
    void deleteByNomeQuery(@Param("nome") String nome);

    //
    //@Query(" select * from Cliente c left join pedidp p on c.pedido where c.id =:id ")
    //@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    //Cliente findClienteFetchPedidos(@Param("id") Integer id);

    @Transactional
    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );

}

//    @Modifying
//    @Query(value = "update rule set order_id=?2 where id=?1", nativeQuery = true)
//    void updateOrderIdbyId(int id, int newOrderId);

//DELETE FROM CLIENTE WHERE ID = 2

//  @Query(value=""DELETE FROM Backlog b WHERE b.code = :code")
//    @Query(value = "delete from cliente c where c.nome =:nome")
//    @Modifying
//    @Query(value = " delete from cliente c where c.nome = :nome")
//     void deleteByNome(@Param("nome") String nome);
//
//    boolean existsByNome(String nome);


//@Query(" select c from cliente c left join fetch c.pedido where c.id = :id  ")
//Cliente findClienteFetchPedidos( @Param("id") Integer id );
//}



//    private static String INSERT = "insert into CLIENTE (nome) values (?) ";
//    private static String SELECTALL = "SELECT * FROM CLIENTE";
//    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";
//    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

//    private RowMapper<Cliente> obertClienteMapper() {
//        return new RowMapper<Cliente>() {
//            @Override
//            public Cliente mapRow(ResultSet result, int rowNum) throws SQLException {
//                Integer id = result.getInt("id");
//                String nome = result.getString("nome");
//                return new Cliente(id, nome);
//            }
//        };
//    }
//    @Autowired
//    public JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public Cliente salvar(Cliente cliente) {
//
//        //jdbcTemplate.update( INSERT, new Object[] {cliente.getNome()});
//        entityManager.persist(cliente);
//        return cliente;
//    }
//
//    @Transactional
//    public Cliente atualizar(Cliente cliente) {
//        entityManager.merge(cliente);
////        jdbcTemplate.update(UPDATE, new Object[]{
////            cliente.getNome(),cliente.getId()
////        });
//        return cliente;
//    }
//
//    @Transactional
//    public void deletar(Cliente cliente) {
//        //deletar(cliente.getId());
//        if(!entityManager.contains(cliente)){
//            cliente = entityManager.merge(cliente);
//        }
//        entityManager.remove(cliente);
//    }
//
//
//    public void deletar(Integer id) {
//        //jdbcTemplate.update(DELETE, new Object[]{id});
//        Cliente cliente = entityManager.find(Cliente.class, id);
//        deletar(cliente);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> buscarPorNome(String nome) {
//        String jpql = " select c from Cliente c where c.nome like :nome ";
//        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//        query.setParameter("nome","%" + nome + "%");
//        return query.getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> obterTodos() {
//        return entityManager
//                .createQuery("from Cliente", Cliente.class)
//                .getResultList();
//        //return jdbcTemplate.query(SELECTALL, obertClienteMapper());
//    }
//
////        return jdbcTemplate.query(
////                SELECTALL.concat(" where nome like ? "),
////                new Object []{"%" +  nome + "%"},
////                obertClienteMapper());
////    }
//}
