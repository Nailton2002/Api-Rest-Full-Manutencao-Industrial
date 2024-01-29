package com.manutencao.industrial.domain.dto.operador.resquest;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorUpRequest {

    private Integer id;
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;
    @NotEmpty(message = "O campo telefone é obrigatório")
    private String telefone;

    public OperadorUpRequest(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.telefone = obj.getTelefone();
    }
}
