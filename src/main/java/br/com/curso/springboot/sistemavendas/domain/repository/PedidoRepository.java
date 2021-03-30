package br.com.curso.springboot.sistemavendas.domain.repository;

import br.com.curso.springboot.sistemavendas.domain.entity.ClienteEntity;
import br.com.curso.springboot.sistemavendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente (ClienteEntity clienteEntity);
}
