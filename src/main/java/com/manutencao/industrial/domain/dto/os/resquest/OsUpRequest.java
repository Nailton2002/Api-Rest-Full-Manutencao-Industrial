package com.manutencao.industrial.domain.dto.os.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsUpRequest {

    private Integer id;
    private Integer status;
    private Integer tecnico;
    private Integer operador;
    private Integer prioridade;
    private String observacoes;

    public Prioridade getPrioridade() { return Prioridade.toEnum(this.prioridade); }

    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return Status.toEnum(this.status); }

    public void setStatus(Integer status) { this.status = status; }

}
