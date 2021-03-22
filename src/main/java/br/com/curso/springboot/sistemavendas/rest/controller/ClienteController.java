package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.Cliente;
import br.com.curso.springboot.sistemavendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public ResponseEntity listarTodosClientes(){

        List<Cliente> clientes = clienteService.listarClientes();

        if(clientes.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum cliente localizado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("cliente/filtro")
    public ResponseEntity buscarClientePorNome(@PathParam("nome") String nome){

        List<Cliente> cliente = clienteService.buscarPorNome(nome);

        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente não localizado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity buscarClientePorId(@PathVariable("id") Integer id){

        Optional<Cliente> cliente = clienteService.buscarPorId(id);

        if(cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não localizado.");
    }

    @PostMapping("/cliente")
    public ResponseEntity salvarCliente(@RequestBody Cliente cliente){

        Cliente clienteNovo = clienteService.salvarCliente(cliente);

        if(cliente.getNmCliente().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome é obrigatório");
        }
        if(cliente.getNrRg().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O rg é obrigatório");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso" + clienteNovo);
    }


    @PutMapping("/cliente/{id}")
    public ResponseEntity atualizarCliente(@RequestBody Cliente cliente, @PathVariable("id") Integer id){

        Boolean retorno = clienteService.atualizarCliente(cliente, id);

        if (retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso." + cliente);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não foi atualizado.");
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity deletarCliente(@PathVariable("id") Integer id){

        Boolean retorno = clienteService.deletarCliente(id);

        if(retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não foi deletado.");
    }

}
