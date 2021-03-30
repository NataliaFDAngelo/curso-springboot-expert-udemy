package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.ClienteEntity;
import br.com.curso.springboot.sistemavendas.rest.dto.ClienteDTO;
import br.com.curso.springboot.sistemavendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public ResponseEntity listarTodosClientes(){

        List<ClienteDTO> clienteDTOS = clienteService.getClientesDTO();

        if(clienteDTOS.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum cliente localizado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteDTOS);
    }

//    @GetMapping("cliente/filtro")
//    public ResponseEntity buscarClientePorNome(@PathParam("nome") String nome){
//
//        List<ClienteEntity> clienteEntity = clienteService.buscarPorNome(nome);
//
//        if(clienteEntity.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente não localizado.");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(clienteEntity);
//    }
//
//    @GetMapping("cliente/{id}")
//    public ResponseEntity buscarClientePorId(@PathVariable("id") Integer id){
//
//        Optional<ClienteEntity> cliente = clienteService.buscarPorId(id);
//
//        if(cliente.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não localizado.");
//    }
//
//    @PostMapping("/cliente")
//    public ResponseEntity salvarCliente(@RequestBody ClienteEntity clienteEntity){
//
//        ClienteEntity clienteEntityNovo = clienteService.salvarCliente(clienteEntity);
//
//        if(clienteEntity.getNmCliente().isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome é obrigatório");
//        }
//        if(clienteEntity.getNrCpf().isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O rg é obrigatório");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso" + clienteEntityNovo);
//    }
//
//
//    @PutMapping("/cliente/{id}")
//    public ResponseEntity atualizarCliente(@RequestBody ClienteEntity clienteEntity, @PathVariable("id") Integer id){
//
//        Boolean retorno = clienteService.atualizarCliente(clienteEntity, id);
//
//        if (retorno){
//            return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso." + clienteEntity);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não foi atualizado.");
//    }
//
//    @DeleteMapping("cliente/{id}")
//    public ResponseEntity deletarCliente(@PathVariable("id") Integer id){
//
//        Boolean retorno = clienteService.deletarCliente(id);
//
//        if(retorno){
//            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não foi deletado.");
//    }

}
