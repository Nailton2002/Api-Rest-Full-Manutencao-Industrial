package com.manutencao.industrial.domain.dto.tecnico.resquest;

import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoUpRequest {

    private Integer id;
    private String nome;
    private String telefone;

    public TecnicoUpRequest(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
