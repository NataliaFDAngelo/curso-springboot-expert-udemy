package br.com.curso.springboot.sistemavendas.domain.repository;

import br.com.curso.springboot.sistemavendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
