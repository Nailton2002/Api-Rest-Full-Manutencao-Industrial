package com.manutencao.industrial.application.operador.dto.view;

import com.manutencao.industrial.domain.operador.model.Operador;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class OperadorListView {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;

    public OperadorListView() { }

    public OperadorListView(Operador obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
