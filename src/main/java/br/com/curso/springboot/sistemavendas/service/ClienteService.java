package br.com.curso.springboot.sistemavendas.service;

import br.com.curso.springboot.sistemavendas.domain.entity.ClienteEntity;
import br.com.curso.springboot.sistemavendas.domain.repository.ClienteRepository;
import br.com.curso.springboot.sistemavendas.rest.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Conversao de DTO para Entity
    public ClienteEntity conversaoClienteEntity(ClienteDTO clienteDTO, ClienteEntity clienteEntity){

        clienteEntity.setNmCliente(clienteDTO.getNome());
        clienteEntity.setNrCpf(clienteDTO.getCpf());
        clienteEntity.setDtNascto(clienteDTO.getNascimento());

        return clienteEntity;
    }

    // Conversão de Entity para DTO
    public ClienteDTO conversaoClienteDTO(ClienteEntity clienteEntity, ClienteDTO clienteDTO){

        clienteDTO.setNome(clienteEntity.getNmCliente());
        clienteDTO.setNascimento(clienteEntity.getDtNascto());
        clienteDTO.setCpf(clienteEntity.getNrCpf());

        return clienteDTO;
    }

    //Conversao de ListaDTO para ListaEntity
    public List<ClienteEntity> conversaoClientesEntity(List<ClienteDTO> clienteDTOS, List<ClienteEntity> clienteEntities){

        for (ClienteDTO clienteDTO : clienteDTOS){
            ClienteEntity clienteEntity = new ClienteEntity();

            clienteEntity = conversaoClienteEntity(clienteDTO, clienteEntity);

            clienteEntities.add(clienteEntity);
        }

        return clienteEntities;
    }

    //Conversao ListaEntity para ListaDTO
    public List<ClienteDTO> conversaoClientesDTO (List<ClienteEntity> clienteEntities, List<ClienteDTO> clienteDTOS){

        for(ClienteEntity clienteEntity : clienteEntities){
            ClienteDTO clienteDTO = new ClienteDTO();

            clienteDTO = conversaoClienteDTO(clienteEntity, clienteDTO);

            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;

    }

    //Retornando DTO
    public ClienteDTO getClienteDTO(Integer idCliente){
        ClienteEntity clienteEntity = getCliente(idCliente);
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO = conversaoClienteDTO(clienteEntity, clienteDTO);

        return clienteDTO;
    }

    public List<ClienteDTO> getClientesDTO(){
        List<ClienteEntity> clienteEntities = getClientes();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();

        clienteDTOS = conversaoClientesDTO(clienteEntities, clienteDTOS);

        return clienteDTOS;
    }

    //Retornando Entity
    public ClienteEntity getCliente(Integer idCliente){
        Optional<ClienteEntity> optional = clienteRepository.findById(idCliente);
        return optional.get();
    }

    public List<ClienteEntity> getClientes(){
        return clienteRepository.findAll();
    }

    public List<ClienteEntity> buscarPorNome(String nmCliente){
        return clienteRepository.findByNmClienteContaining(nmCliente);
    }

    //Salvar DTO
//    @Transactional
//    public String cadastrarCliente(ClienteDTO clienteDTO){
//
//        ClienteEntity clienteEntity = new ClienteEntity();
//
//        String cpf = clienteEntity.getNrCpf();
//        List<ClienteEntity> clienteExistente = clienteRepository.findByNrCpf(cpf);
//
//        if(clienteExistente.isEmpty()){
//            clienteEntity = conversaoClienteEntity(clienteDTO, clienteEntity);
//            clienteRepository.save(clienteEntity);
//
//            return "Cliente cadastrado com sucesso.";
//        }
//
//        return "Erro: CPF já cadastrado.";
//    }
//
//    @Transactional
//    public String alterarCliente(ClienteDTO clienteDTO, Integer idCliente){
//
//        ClienteEntity clienteEntity = getCliente(idCliente);
//        clienteEntity = conversaoClienteEntity(clienteDTO, clienteEntity);
//
//        clienteEntity = clienteRepository.save(clienteEntity);
//
//        return "Alteração realizada com sucesso.";
//    }
//
//    @Transactional
//    public String excluirCliente(Integer idCliente){
//
//        clienteRepository.deleteById(idCliente);
//
//        return "Exclusão do cliente realizada com sucesso.";
//    }

}
