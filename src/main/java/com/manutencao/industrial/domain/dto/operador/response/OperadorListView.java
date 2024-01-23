package com.manutencao.industrial.domain.dto.operador.response;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.Data;

@Data
public class OperadorListView {

    private Integer id;
    private String nome;

    public OperadorListView() { }

    public OperadorListView(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
