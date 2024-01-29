package com.manutencao.industrial.domain.dto.os.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsResponse {

    private Integer id;
    private Integer status;
    private Integer tecnico;
    private Integer operador;
    private Integer prioridade;
    private String observacoes;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    public OsResponse(OrdemServico obj) {
        this.id = obj.getId();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.operador = obj.getOperador().getId();
        this.observacoes = obj.getObservacoes();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCod();
    }

    public Prioridade getPrioridade() { return Prioridade.toEnum(this.prioridade); }

    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return Status.toEnum(this.status); }

    public void setStatus(Integer status) { this.status = status; }
}
