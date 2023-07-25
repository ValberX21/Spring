package com.valber.domain.repository;

import com.valber.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Transactional
    @Modifying
    @Query(value = " select * from cliente c where c.nome = :nome ", nativeQuery = true)
    List<Cliente> findNomeByLike(@Param("nome") String nome);

//    @Query(" delete from CLIENTE c where c.nome =:nome ")
//    @Modifying
//    void deleteByNome(String nome);
//
//    boolean existsByNome(String nome);

    @Autowired
    @Query(" select c from cliente c left join fetch c.pedidos where c.id =:id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}



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
