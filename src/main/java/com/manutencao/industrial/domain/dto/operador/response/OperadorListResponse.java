package com.manutencao.industrial.domain.dto.operador.response;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorListResponse {

    private Integer id;
    private String nome;
    private String telefone;

    public OperadorListResponse(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }

}
