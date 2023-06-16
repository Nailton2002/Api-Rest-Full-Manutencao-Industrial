package com.manutencao.industrial.application.operador.dto.form;

import com.manutencao.industrial.domain.operador.model.Operador;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class OperadorUpForm {

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido")
    private String nome;
    @NotEmpty(message = "O campo CPF é requerido")
    private String cpf;
    @NotEmpty(message = "O campo TELEFONE é requerido")
    private String telefone;

    public OperadorUpForm() { }

    public OperadorUpForm(Operador obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}
