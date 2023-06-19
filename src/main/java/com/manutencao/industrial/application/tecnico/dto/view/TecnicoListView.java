package com.manutencao.industrial.application.tecnico.dto.view;

import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TecnicoListView {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;

    public TecnicoListView() {
        super();
    }

    public TecnicoListView(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
