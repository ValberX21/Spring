package com.valber.domain.repository;

import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface Pedidos  extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Transactional
           //select p from Pedido p left join fetch p.itens where p.id = :id
    @Query(" select p from Pedido p left join fetch p.itens where p.id =:id")
    Optional<Pedido> findByIfFetchItens(@Param("id") Integer id);
}
