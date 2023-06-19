package com.manutencao.industrial.domain.operador.model;

import com.manutencao.industrial.application.operador.dto.form.OperadorForm;
import com.manutencao.industrial.application.operador.dto.form.OperadorUpForm;
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
public class Operador extends Funcionario {

    @OneToMany(mappedBy = "operador")
    private List<OrdemServico> list = new ArrayList<>();

    public Operador(OperadorForm form){
        this.setId(form.getId());
        this.setNome(form.getNome());
        this.setCpf(form.getCpf());
        this.setTelefone(form.getTelefone());
    }

    public void atualizarOperador(OperadorUpForm upForm){
        if (upForm.getNome() != null){
            this.setNome(upForm.getNome());
        }
        if (upForm.getNome() != null){
            this.setTelefone(upForm.getTelefone());
        }
    }

    public Operador() {}

    public Operador(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OrdemServico> getList() {
        return list;
    }

    public void setList(List<OrdemServico> list) {
        this.list = list;
    }
}
