package com.manutencao.industrial.domain.dto.tecnico.response;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoListRespose {

    private Integer id;
    private String nome;
    private String telefone;

    public TecnicoListRespose(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }


}
