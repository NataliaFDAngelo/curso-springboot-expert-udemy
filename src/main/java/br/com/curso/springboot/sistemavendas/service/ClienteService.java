package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.Cliente;
import br.com.curso.springboot.sistemavendas.domain.repository.ClienteRepository;
import com.mysql.cj.xdevapi.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarPorNome(String nmCliente){
        return clienteRepository.findByNmClienteContaining(nmCliente);
    }

    public Cliente salvarCliente(Cliente cliente){

        Cliente clienteEntity = new Cliente();

        clienteEntity.setNmCliente(cliente.getNmCliente());
        clienteEntity.setDtNascto(cliente.getDtNascto());
        clienteEntity.setNrRg(cliente.getNrRg());

        return clienteRepository.save(clienteEntity);

    }

    public boolean atualizarCliente(Cliente cliente, Integer id){

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            Cliente clienteEntity = clienteOptional.get();
            clienteEntity.setNmCliente(cliente.getNmCliente());
            clienteEntity.setDtNascto(cliente.getDtNascto());
            clienteEntity.setNrRg(cliente.getNrRg());

            clienteRepository.save(clienteEntity);

            return true;
        }
        return false;
    }

    public boolean deletarCliente(Integer id){

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;

    }



}
