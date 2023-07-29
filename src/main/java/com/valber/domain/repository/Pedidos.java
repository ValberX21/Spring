package com.valber.domain.repository;

import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos  extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
