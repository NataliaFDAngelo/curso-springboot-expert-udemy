package br.com.curso.springboot.sistemavendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_CLIENTE")
@Data
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "NM_CLIENTE", length = 100)
    private String nmCliente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_NASCTO")
    private Date dtNascto;

    @Column(name = "NR_CPF", length = 20)
    private String nrCpf;

}
