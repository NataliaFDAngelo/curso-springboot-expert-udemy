package br.com.curso.springboot.sistemavendas.rest.controller;

import br.com.curso.springboot.sistemavendas.domain.entity.ProdutoEntity;
import br.com.curso.springboot.sistemavendas.rest.dto.ProdutoDTO;
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
    public ResponseEntity listarTodosProdutos(){

        List<ProdutoDTO> produtoDTOS = produtoService.getProdutosDTO();

        if(produtoDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTOS);
    }

    @GetMapping("/produto/filtro")
    public ResponseEntity buscarProdutoNome(@PathParam ("dsProduto") String dsProduto){

        List<ProdutoEntity> produtoEntityBuscado = produtoService.getProdutoByName(dsProduto);

        if(produtoEntityBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoEntityBuscado);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity buscaProdutoId(@PathVariable("id") Integer id){

        ProdutoEntity produtoOptional = produtoService.getProduto(id);

        if(produtoOptional == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional);

    }

    @PostMapping("/produto")
    public ResponseEntity salvarProduto(@RequestBody ProdutoDTO produtoDTO){

        String produtoNovoDTO = produtoService.cadastrarProduto(produtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso.");
    }

    @PutMapping("produto/{cdProduto}")
    public ResponseEntity altualizarProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable("cdProduto") Integer cdProduto){

        String retornoProduto = produtoService.alterarProduto(produtoDTO, cdProduto);

        return ResponseEntity.status(HttpStatus.OK).body(retornoProduto);

    }

    @DeleteMapping("/produto/{cdProduto}")
    public ResponseEntity deletarProduto(@PathVariable("cdProduto") Integer cdProduto){

        String retorno = produtoService.excluirProduto(cdProduto);

        if(retorno == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não deletado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }


}
