package com.valber.service.impl;

import com.valber.domain.entity.Cliente;
import com.valber.domain.entity.ItemPedido;
import com.valber.domain.entity.Pedido;
import com.valber.domain.entity.Produto;
import com.valber.domain.enums.StatusPedido;
import com.valber.domain.repository.Clientes;
import com.valber.domain.repository.ItemsPedido;
import com.valber.domain.repository.Pedidos;
import com.valber.domain.repository.Produtos;
import com.valber.exception.PedidoNaoEncontradoException;
import com.valber.exception.RegraNegocioException;
import com.valber.rest.dto.ItemPedidoDTO;
import com.valber.rest.dto.PedidoDto;
import com.valber.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidos;
    private final Clientes clientes;
    private final Produtos produtos;
    private final ItemsPedido itemsPedidos;

    private BigDecimal valorTotal =  new BigDecimal(0);;

    @Override
    @Transactional
    public Pedido salvar(PedidoDto dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientes
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = convertItems(pedido, dto.getItems());
        pedidos.save(pedido);
        itemsPedidos.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        pedido.setTotal(valorTotal);
        return  pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoComplete(Integer id) {
        return pedidos.findByIfFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidos
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidos.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException(""));
    }

    private List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtos
                            .findById(idProduto)
                            .orElseThrow(()-> new RegraNegocioException("Codigo de produto invalido " + idProduto
                            ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    valorTotal = valorTotal
                            .add(new BigDecimal(produto.getPreco().toString())
                                    .multiply(new BigDecimal(itemPedido.getQuantidade())));
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}