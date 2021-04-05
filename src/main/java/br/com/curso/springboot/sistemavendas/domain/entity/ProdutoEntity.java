package br.com.curso.springboot.sistemavendas.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
@Data
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PRODUTO")
    private Integer cdProduto;

    @Column(name = "DS_PRODUTO")
    private String dsProduto;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

}
