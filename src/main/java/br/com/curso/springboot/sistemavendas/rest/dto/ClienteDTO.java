package br.com.curso.springboot.sistemavendas.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClienteDTO {

    private String nome;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nascimento;

    private String cpf;
}
