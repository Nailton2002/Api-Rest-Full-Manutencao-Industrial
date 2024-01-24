package com.manutencao.industrial.domain.entity.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Operador")
@Table(name = "tb_operador")
public class Operador extends Funcionario {

    @OneToMany(mappedBy = "operador")
    private List<OrdemServico> list = new ArrayList<>();

    public Operador(OperadorRequest form){
        this.setId(form.getId());
        this.setNome(form.getNome());
        this.setCpf(form.getCpf());
        this.setTelefone(form.getTelefone());
    }

    public void atualizarOperador(OperadorUpRequest upRequest){
        if (upRequest.getNome() != null){
            this.setNome(upRequest.getNome());
        }
        if (upRequest.getNome() != null){
            this.setTelefone(upRequest.getTelefone());
        }
    }

}
