package br.com.curso.springboot.sistemavendas.rest.dto;

import lombok.Data;

@Data
public class ItemPedidoDTO {

    private Integer produto;

    private Integer quantidade;

}
