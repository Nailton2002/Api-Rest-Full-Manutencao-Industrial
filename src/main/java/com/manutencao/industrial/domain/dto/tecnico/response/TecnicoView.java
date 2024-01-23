package com.manutencao.industrial.domain.dto.tecnico.response;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoView {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public TecnicoView(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public TecnicoView(TecnicoForm request) {
        this.id = request.getId();
        this.nome = request.getNome();
        this.cpf = request.getCpf();
        this.telefone = request.getTelefone();
    }
}
