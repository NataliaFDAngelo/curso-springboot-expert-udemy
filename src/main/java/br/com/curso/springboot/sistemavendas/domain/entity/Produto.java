package br.com.curso.springboot.sistemavendas.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {

    private Integer id;

    private String descricao;

    private BigDecimal precoUnitario;

}
