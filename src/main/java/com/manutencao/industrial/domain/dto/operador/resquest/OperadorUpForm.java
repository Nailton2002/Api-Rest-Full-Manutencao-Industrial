package com.manutencao.industrial.domain.dto.operador.resquest;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OperadorUpForm {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;
    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    public OperadorUpForm() { }

    public OperadorUpForm(Operador obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
