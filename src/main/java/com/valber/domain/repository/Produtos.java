package com.valber.domain.repository;

import com.valber.domain.entity.ItemPedido;
import com.valber.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos  extends JpaRepository<Produto, Integer> {
}
