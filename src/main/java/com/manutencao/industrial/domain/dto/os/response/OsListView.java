package com.manutencao.industrial.domain.dto.os.response;

import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class OsListView {

    private Integer id;
    private Integer status;
    private Integer tecnico;
    private Integer operador;
    private Integer prioridade;
    private String observacoes;

    public OsListView(OrdemServico obj) {
        this.id = obj.getId();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.operador = obj.getOperador().getId();
        this.prioridade = obj.getPrioridade().getCod();
        this.observacoes = obj.getObservacoes();
    }

}
