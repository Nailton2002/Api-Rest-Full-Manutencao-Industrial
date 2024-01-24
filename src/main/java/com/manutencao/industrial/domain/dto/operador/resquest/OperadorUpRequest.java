package com.manutencao.industrial.domain.dto.operador.resquest;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OperadorUpRequest {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;
    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    public OperadorUpRequest() { }

    public OperadorUpRequest(Operador obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
