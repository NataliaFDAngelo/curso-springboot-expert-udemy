package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.Produto;
import br.com.curso.springboot.sistemavendas.service.ProdutoService;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

        List<Produto> produtos = produtoService.listarProdutos();

        if(produtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/produto/filtro")
    public ResponseEntity buscarProdutoNome(@PathParam ("nome") String nome){

        List<Produto> produtoBuscado = produtoService.buscarProdutoPorNome(nome);

        if(produtoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoBuscado);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity buscaProdutoId(@PathVariable("id") Integer id){

        Optional<Produto> produtoOptional = produtoService.buscarProdutoPorId(id);

        if(produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(produtoOptional);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");

    }

    @PostMapping("/produto")
    public ResponseEntity salvarProduto(@RequestBody Produto produto){

        Produto produtoNovo = produtoService.salvarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso" + produtoNovo);

    }

    @PutMapping("produto/{id}")
    public ResponseEntity alterarProduto(@RequestBody Produto produto, @PathVariable("id") Integer id){

        Boolean retorno = produtoService.atualizarProduto(produto, id);

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
