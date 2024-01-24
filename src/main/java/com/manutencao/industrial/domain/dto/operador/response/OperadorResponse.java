package com.manutencao.industrial.domain.dto.operador.response;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorResponse {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    public OperadorResponse(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public OperadorResponse(OperadorRequest request) {
        id = request.getId();
        nome = request.getNome();
        cpf = request.getCpf();
        telefone = request.getTelefone();
    }

}
