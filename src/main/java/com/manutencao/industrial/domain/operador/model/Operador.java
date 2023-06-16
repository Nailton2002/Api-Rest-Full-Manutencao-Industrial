package com.manutencao.industrial.domain.operador.model;

import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import org.apache.tomcat.jni.OS;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Operador extends Funcionario {

    @OneToMany(mappedBy = "operador")
    private List<OS> list = new ArrayList<>();

    public Operador() {
        super();
    }

    public Operador(Integer id, String nome, String cpf, String telefone, String senha) {
        super(id, nome, cpf, telefone, senha);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
