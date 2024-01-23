package com.manutencao.industrial.domain.dto.tecnico.resquest;

import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoForm {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public TecnicoForm(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
