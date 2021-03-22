package br.com.curso.springboot.sistemavendas.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_PRODUTO")
    private Integer cdProduto;

    @Column(name = "DS_PRODUTO")
    private String dsProduto;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

}
