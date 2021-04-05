package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.ProdutoEntity;
import br.com.curso.springboot.sistemavendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produto")
    public ResponseEntity listarProdutos(){

        List<ProdutoEntity> produtoEntities = produtoService.listarProdutos();

        if(produtoEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoEntities);
    }

    @GetMapping("/produto/filtro")
    public ResponseEntity buscarProdutoNome(@PathParam ("nome") String nome){

        List<ProdutoEntity> produtoEntityBuscado = produtoService.buscarProdutoPorNome(nome);

        if(produtoEntityBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoEntityBuscado);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity buscaProdutoId(@PathVariable("id") Integer id){

        Optional<ProdutoEntity> produtoOptional = produtoService.buscarProdutoPorId(id);

        if(produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(produtoOptional);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");

    }

    @PostMapping("/produto")
    public ResponseEntity salvarProduto(@RequestBody ProdutoEntity produtoEntity){

        ProdutoEntity produtoEntityNovo = produtoService.salvarProduto(produtoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso" + produtoEntityNovo);

    }

    @PutMapping("produto/{id}")
    public ResponseEntity alterarProduto(@RequestBody ProdutoEntity produtoEntity, @PathVariable("id") Integer id){

        Boolean retorno = produtoService.atualizarProduto(produtoEntity, id);

        if(retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Produto alterado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não alterado.");
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity excluirProduto(@PathVariable("id") Integer id){

        Boolean retorno = produtoService.deletarProduto(id);

        if(retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não deletado.");
    }


}
