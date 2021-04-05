package br.com.curso.springboot.sistemavendas.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Integer codProduto;

    private String nomeProduto;

    private BigDecimal precoUnitario;

}
