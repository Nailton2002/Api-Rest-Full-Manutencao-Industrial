package com.manutencao.industrial.domain.dto.operador.resquest;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorRequest {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public OperadorRequest(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
