package br.com.curso.springboot.sistemavendas.domain.repository;

import br.com.curso.springboot.sistemavendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
