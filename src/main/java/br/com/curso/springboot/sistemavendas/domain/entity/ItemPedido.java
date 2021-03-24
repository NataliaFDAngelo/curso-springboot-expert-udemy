package br.com.curso.springboot.sistemavendas.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_PEDIDO_ITEM")
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_ITEM")
    private Integer idPedidoItem;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "CD_PRODUTO")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
