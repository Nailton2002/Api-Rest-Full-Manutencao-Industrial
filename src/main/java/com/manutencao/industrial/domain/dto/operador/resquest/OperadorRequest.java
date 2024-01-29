package com.manutencao.industrial.domain.dto.operador.resquest;

import com.manutencao.industrial.domain.entity.operador.Operador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorRequest {

    private Integer id;
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;
    @CPF @NotEmpty(message = "O campo CPF é obrigatório")
    private String cpf;
    @NotEmpty(message = "O campo nome é obrigatório")
    private String telefone;

    public OperadorRequest(Operador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
