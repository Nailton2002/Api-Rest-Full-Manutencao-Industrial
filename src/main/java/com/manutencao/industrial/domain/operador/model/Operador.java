package com.manutencao.industrial.domain.operador.model;

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
