package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.Pedido;
import br.com.curso.springboot.sistemavendas.rest.dto.PedidoDTO;
import br.com.curso.springboot.sistemavendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedido")
    public ResponseEntity listarTodosPedidos(){

        List<Pedido> pedidos = pedidoService.listarPedidos();

        if(pedidos.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum pedido encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);

    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity buscarPedidoId(@PathVariable("id") Integer id){

        Optional<Pedido> pedido = pedidoService.buscarPedidoPorId(id);

        if(pedido.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido não encontrado.");
    }

    @PostMapping("/pedido")
    public ResponseEntity save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso" + pedidoDTO);
    }


}
