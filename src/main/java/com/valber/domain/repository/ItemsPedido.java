package com.valber.domain.repository;

import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
