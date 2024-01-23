package com.manutencao.industrial.domain.dto.os.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OsView {

    private Integer id;
    private Integer status;
    private Integer tecnico;
    private Integer operador;
    private Integer prioridade;
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;

    public OsView(OrdemServico obj) {
        this.id = obj.getId();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.operador = obj.getOperador().getId();
        this.observacoes = obj.getObservacoes();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCod();
    }
}
