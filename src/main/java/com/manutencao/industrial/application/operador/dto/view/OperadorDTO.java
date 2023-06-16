package com.manutencao.industrial.application.operador.dto.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manutencao.industrial.domain.operador.model.Operador;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class OperadorDTO {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;
    @NotEmpty(message = "O campo CPF é requerido")
    private String cpf;
    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    public OperadorDTO() { }

    public OperadorDTO(Operador obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
