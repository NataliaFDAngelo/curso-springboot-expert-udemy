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

    @GetMapping("cliente/filtro")
    public ResponseEntity buscarClientePorNome(@PathParam("nome") String nome){

        List<ClienteEntity> clienteEntity = clienteService.buscarPorNome(nome);

        if(clienteEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente não localizado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteEntity);
    }

    @GetMapping("cliente/{idCliente}")
    public ResponseEntity buscarClientePorId(@PathVariable("idCliente") Integer idCliente){

        ClienteDTO cliente = clienteService.getClienteDTO(idCliente);

        if(cliente == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("cliente/cpf")
    public ResponseEntity buscarClientePorCpf(@PathParam("cpf") String cpf) {

        List<ClienteEntity> clienteEntityList = clienteService.consultarClientePorCpf(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(clienteEntityList);
    }


    @PostMapping("/cliente")
    public ResponseEntity salvarCliente(@RequestBody ClienteDTO clienteDTO){

        String clienteDTONovo = clienteService.cadastrarCliente(clienteDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso.");
    }


    @PutMapping("/cliente/{id}")
    public ResponseEntity atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable("id") Integer id){

        String retornoCiente = clienteService.alterarCliente(clienteDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(retornoCiente);
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity deletarCliente(@PathVariable("id") Integer id){

        String retorno = clienteService.excluirCliente(id);

        if(retorno.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cliente não foi deletado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");

    }

}
