package com.valber.service;

import com.valber.domain.entity.Pedido;
import com.valber.domain.enums.StatusPedido;
import com.valber.rest.dto.PedidoDto;

import java.time.Period;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDto dto);

    Optional<Pedido> obterPedidoComplete(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPeoido);
}
