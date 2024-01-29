package com.manutencao.industrial.domain.dto.tecnico.resquest;

import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoUpRequest {

    private Integer id;
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;
    @NotEmpty(message = "O campo telefone é obrigatório")
    private String telefone;

    public TecnicoUpRequest(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
