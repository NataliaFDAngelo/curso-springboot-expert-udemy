package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.ClienteEntity;
import br.com.curso.springboot.sistemavendas.domain.entity.ProdutoEntity;
import br.com.curso.springboot.sistemavendas.domain.repository.ProdutoRepository;
import br.com.curso.springboot.sistemavendas.rest.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Conversao de DTO para Entity
    public ProdutoEntity conversaoProdutoEntity(ProdutoDTO produtoDTO, ProdutoEntity produtoEntity){

        produtoEntity.setDsProduto(produtoDTO.getNomeProduto());
        produtoEntity.setPrecoUnitario(produtoDTO.getPrecoUnitario());

        return produtoEntity;
    }

    // Conversão de Entity para DTO
    public ProdutoDTO conversaoProdutoDTO(ProdutoEntity produtoEntity, ProdutoDTO produtoDTO){

        produtoDTO.setCodProduto(produtoEntity.getCdProduto());
        produtoDTO.setNomeProduto(produtoEntity.getDsProduto());
        produtoDTO.setPrecoUnitario(produtoEntity.getPrecoUnitario());

        return produtoDTO;
    }

    //Conversao de ListaDTO para ListaEntity
    public List<ProdutoEntity> conversaoProdutosEntity(List<ProdutoDTO> produtosDTOS, List<ProdutoEntity> produtosEntities){

        for(ProdutoDTO produtoDTO : produtosDTOS){

            ProdutoEntity produtoEntity = new ProdutoEntity();

            produtoEntity = conversaoProdutoEntity(produtoDTO, produtoEntity);

            produtosEntities.add(produtoEntity);
        }
        return produtosEntities;
    }

    //Conversao ListaEntity para ListaDTO
    public List<ProdutoDTO> conversaoProdutosDTO(List<ProdutoEntity> produtosEntities, List<ProdutoDTO> produtosDTOS){

        for(ProdutoEntity produtoEntity : produtosEntities){

            ProdutoDTO produtoDTO = new ProdutoDTO();

            produtoDTO = conversaoProdutoDTO(produtoEntity, produtoDTO);

            produtosDTOS.add(produtoDTO);
        }
        return produtosDTOS;
    }

    //Retornando DTO
    public ProdutoDTO getProdutoDTO(Integer cdProduto){

        ProdutoEntity produtoEntity = getProduto(cdProduto);

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO = conversaoProdutoDTO(produtoEntity, produtoDTO);

        return produtoDTO;
    }

    public List<ProdutoDTO> getProdutosDTO(){
        List<ProdutoEntity> produtosEntities = getProdutos();
        List<ProdutoDTO> produtosDTOS = new ArrayList<>();

        produtosDTOS = conversaoProdutosDTO(produtosEntities, produtosDTOS);

        return produtosDTOS;
    }

    //Retornando Entity
    public ProdutoEntity getProduto(Integer cdProduto){
        Optional<ProdutoEntity> optional = produtoRepository.findById(cdProduto);
        return optional.get();
    }

    public List<ProdutoEntity> getProdutos(){
        return produtoRepository.findAll();
    }

    public List<ProdutoEntity> getProdutoByName(String dsProduto){
        return produtoRepository.findByDsProdutoContaining(dsProduto);
    }

    //Salvar DTO
    @Transactional
    public String cadastrarProduto(ProdutoDTO produtoDTO){
        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity = conversaoProdutoEntity(produtoDTO, produtoEntity);

        produtoRepository.save(produtoEntity);

        return "Produto cadastrado com sucesso.";
    }

    //Alterar DTO
    @Transactional
    public String alterarProduto(ProdutoDTO produtoDTO, Integer cdProduto){

        ProdutoEntity produtoEntity = getProduto(cdProduto);
        produtoEntity = conversaoProdutoEntity(produtoDTO, produtoEntity);

        produtoRepository.save(produtoEntity);

        return "Produto alterado com sucesso.";
    }

    //Excluir DTO
    @Transactional
    public String excluirProduto(Integer cdProduto){

        produtoRepository.deleteById(cdProduto);

        return "Produto alterado com sucesso.";
    }

}
