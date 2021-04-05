package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.ProdutoEntity;
import br.com.curso.springboot.sistemavendas.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoEntity> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> buscarProdutoPorId(Integer id){
        return produtoRepository.findById(id);
    }

    public List<ProdutoEntity> buscarProdutoPorNome(String nome){
        return produtoRepository.findByDsProdutoContaining(nome);
    }

    public ProdutoEntity salvarProduto(ProdutoEntity produto){

        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity.setDsProduto(produto.getDsProduto());
        produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());

        return produtoRepository.save(produtoEntity);
    }

    public Boolean atualizarProduto(ProdutoEntity produto, Integer id){

        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()){
            ProdutoEntity produtoEntity = produtoOptional.get();

            produtoEntity.setDsProduto(produto.getDsProduto());
            produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());

            produtoRepository.save(produtoEntity);

            return true;
        }
        return false;
    }

    public Boolean deletarProduto(Integer id){

        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()){
            produtoRepository.deleteById(id);

            return true;
        }
        return false;
    }

}
