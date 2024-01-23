package com.manutencao.industrial.domain.dto.tecnico.response;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoListView {

    private Integer id;
    private String nome;
    private String telefone;

    public TecnicoListView(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }

    public TecnicoListView(TecnicoUpForm request) {
        this.id = request.getId();
        this.nome = request.getNome();
        this.telefone = request.getTelefone();
    }

}
