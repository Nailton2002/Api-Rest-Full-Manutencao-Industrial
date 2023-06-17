package com.manutencao.industrial.domain.tecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manutencao.industrial.application.tecnico.dto.form.TecnicoForm;
import com.manutencao.industrial.application.tecnico.dto.form.TecnicoUpForm;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.os.entity.OrdemServico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tecnico extends Funcionario {
    @JsonIgnore @OneToMany(mappedBy = "tecnico")
    private List<OrdemServico> list = new ArrayList<>();

    public Tecnico(TecnicoForm form){
        this.setId(form.getId());
        this.setNome(form.getNome());
        this.setCpf(form.getCpf());
        this.setTelefone(form.getTelefone());
    }

    public void atualizarTecnico(TecnicoUpForm upForm){
        if (upForm.getNome() != null){
            this.setNome(upForm.getNome());
        }
        if (upForm.getNome() != null){
            this.setTelefone(upForm.getTelefone());
        }
    }

    public Tecnico() { }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OrdemServico> getList() {
        return list;
    }

    public void setList(List<OrdemServico> list) {
        this.list = list;
    }
}
