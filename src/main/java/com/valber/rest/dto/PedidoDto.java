package com.valber.rest.dto;

import com.valber.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/*
{
    "cliente":"1",
    "total":100,
    "itens": [
        {
            "produto":1,
            "quantidade":10
        }
    ]
}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;

}
