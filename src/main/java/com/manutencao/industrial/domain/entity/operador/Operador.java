package com.manutencao.industrial.domain.entity.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorForm;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpForm;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Data
@Table(name = "tb_operador")
@Entity(name = "Operador")
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

//    public Operador() {}
//
//    public Operador(Integer id, String nome, String cpf, String telefone) {
//        super(id, nome, cpf, telefone);
//    }
//
//    public List<OrdemServico> getList() {
//        return list;
//    }
//
//    public void setList(List<OrdemServico> list) {
//        this.list = list;
//    }
}
