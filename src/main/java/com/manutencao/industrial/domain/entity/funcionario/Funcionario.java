package com.manutencao.industrial.domain.entity.funcionario;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Data
@Entity(name = "Funcionario")
@Table(name = "tb_funcionario")
@EqualsAndHashCode(of = "id, cpf")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @CPF
    private String cpf;
    private String telefone;
}

