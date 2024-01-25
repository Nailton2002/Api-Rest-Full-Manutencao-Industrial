package com.manutencao.industrial.domain.entity.funcionario;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String cpf;
    private String telefone;
}

