package com.manutencao.industrial.domain.entity.tecnico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
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

    public Tecnico(TecnicoRequest request){
        this.setId(request.getId());
        this.setNome(request.getNome());
        this.setCpf(request.getCpf());
        this.setTelefone(request.getTelefone());
    }

    public void atualizarTecnico(TecnicoUpRequest upRequest){
        if (upRequest.getNome() != null){
            this.setNome(upRequest.getNome());
        }
        if (upRequest.getNome() != null){
            this.setTelefone(upRequest.getTelefone());
        }
    }

}
