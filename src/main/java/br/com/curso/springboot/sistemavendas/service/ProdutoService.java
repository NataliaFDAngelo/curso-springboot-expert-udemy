package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.Produto;
import br.com.curso.springboot.sistemavendas.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Integer id){
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarProdutoPorNome(String nome){
        return produtoRepository.findByDsProdutoContaining(nome);
    }

    public Produto salvarProduto(Produto produto){

        Produto produtoEntity = new Produto();

        produtoEntity.setDsProduto(produto.getDsProduto());
        produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());

        return produtoRepository.save(produtoEntity);
    }

    public Boolean atualizarProduto(Produto produto, Integer id){

        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()){
            Produto produtoEntity = produtoOptional.get();

            produtoEntity.setDsProduto(produto.getDsProduto());
            produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());

            produtoRepository.save(produtoEntity);

            return true;
        }
        return false;
    }

    public Boolean deletarProduto(Integer id){

        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()){
            produtoRepository.deleteById(id);

            return true;
        }
        return false;
    }

}
