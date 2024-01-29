package com.manutencao.industrial.domain.dto.tecnico.resquest;

import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoRequest {

    private Integer id;
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;
    @CPF @NotEmpty(message = "O campo CPF é obrigatório")
    private String cpf;
    @NotEmpty(message = "O campo telefone é obrigatório")
    private String telefone;

    public TecnicoRequest(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
