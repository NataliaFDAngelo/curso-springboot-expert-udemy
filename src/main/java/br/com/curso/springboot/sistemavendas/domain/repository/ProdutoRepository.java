package br.com.curso.springboot.sistemavendas.domain.repository;

import br.com.curso.springboot.sistemavendas.domain.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByDsProdutoContaining(String nome);

}
