package com.manutencao.industrial.domain.dto.tecnico.response;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoResponse {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public TecnicoResponse(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public TecnicoResponse(TecnicoRequest request) {
        this.id = request.getId();
        this.nome = request.getNome();
        this.cpf = request.getCpf();
        this.telefone = request.getTelefone();
    }
}
