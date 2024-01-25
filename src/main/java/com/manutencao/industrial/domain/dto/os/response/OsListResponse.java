package com.manutencao.industrial.domain.dto.os.response;

import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsListResponse {

    private Integer id;
    private Integer status;
    private Integer tecnico;
    private Integer operador;
    private Integer prioridade;
    private String observacoes;

    public OsListResponse(OrdemServico obj) {
        this.id = obj.getId();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.operador = obj.getOperador().getId();
        this.prioridade = obj.getPrioridade().getCod();
        this.observacoes = obj.getObservacoes();
    }

    public Prioridade getPrioridade() { return Prioridade.toEnum(this.prioridade); }

    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return Status.toEnum(this.status); }

    public void setStatus(Integer status) { this.status = status; }
}
