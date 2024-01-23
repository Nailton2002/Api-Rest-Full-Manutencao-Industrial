package com.manutencao.industrial.domain.dto.operador.response;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.Data;

@Data
public class OperadorView {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

//    public OperadorView() { }

    public OperadorView(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
