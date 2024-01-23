package com.manutencao.industrial.domain.entity.tecnico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Tecnico")
@Table(name = "tb_recnico")
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

}
