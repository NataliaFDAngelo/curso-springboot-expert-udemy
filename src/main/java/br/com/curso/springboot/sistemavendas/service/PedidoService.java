package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.Cliente;
import br.com.curso.springboot.sistemavendas.domain.entity.ItemPedido;
import br.com.curso.springboot.sistemavendas.domain.entity.Pedido;
import br.com.curso.springboot.sistemavendas.domain.entity.Produto;
import br.com.curso.springboot.sistemavendas.domain.repository.ClienteRepository;
import br.com.curso.springboot.sistemavendas.domain.repository.ItemsPedidoRepository;
import br.com.curso.springboot.sistemavendas.domain.repository.PedidoRepository;
import br.com.curso.springboot.sistemavendas.domain.repository.ProdutoRepository;
import br.com.curso.springboot.sistemavendas.exception.RegraNegocioException;
import br.com.curso.springboot.sistemavendas.rest.dto.ItemPedidoDTO;
import br.com.curso.springboot.sistemavendas.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private final  PedidoRepository pedidoRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    private final ItemsPedidoRepository itemsPedidoRepository;

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Integer id){
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido salvarPedido(PedidoDTO pedidoDTO){

        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedidoEntity = new Pedido();

        pedidoEntity.setDataPedido(LocalDate.now());
        pedidoEntity.setTotal(pedidoDTO.getTotal());
        pedidoEntity.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedidoEntity, pedidoDTO.getItems());

        pedidoEntity.setItens(itemsPedido);

        pedidoRepository.save(pedidoEntity);
//        itemsPedidoRepository.saveAll(itemsPedido);

        return pedidoEntity;

    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){

        if(items.isEmpty()){
           throw new RegraNegocioException("Não é possível realizar o pedido sem items.");
        }

        return items.stream().map(dto ->{
            Integer idProduto = dto.getProduto();
            Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(
                            () -> new RegraNegocioException("Código de produto inválido: "
                            + idProduto));


            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;
        }).collect(Collectors.toList());

    }

}
