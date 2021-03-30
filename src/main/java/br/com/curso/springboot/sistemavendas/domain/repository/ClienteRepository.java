package br.com.curso.springboot.sistemavendas.domain.repository;

import br.com.curso.springboot.sistemavendas.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    List<ClienteEntity> findByNmClienteContaining(String nome);
    
    List<ClienteEntity> findByNrCpf(String nrCpf);

}
