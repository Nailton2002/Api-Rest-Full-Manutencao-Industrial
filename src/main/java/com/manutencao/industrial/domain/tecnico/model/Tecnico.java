package com.manutencao.industrial.domain.tecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.os.entity.OrdemServico;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Funcionario {
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<OrdemServico> list = new ArrayList<>();

    public Tecnico() {
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone, String senha) {
        super(id, nome, cpf, telefone, senha);
    }

    public List<OrdemServico> getList() {
        return list;
    }

    public void setList(List<OrdemServico> list) {
        this.list = list;
    }
}
