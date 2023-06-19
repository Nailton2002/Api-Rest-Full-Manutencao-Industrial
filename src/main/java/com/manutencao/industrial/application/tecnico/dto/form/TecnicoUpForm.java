package com.manutencao.industrial.application.tecnico.dto.form;

import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TecnicoUpForm {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;
    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    public TecnicoUpForm() {
        super();
    }

    public TecnicoUpForm(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
