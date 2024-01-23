package com.manutencao.industrial.domain.entity.os;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "OrdemServico")
@Table(name = "tb_ordem_servico")
public class OrdemServico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    private String observacoes;
    private Integer status;

    @ManyToOne @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne @JoinColumn(name = "operador_id")
    private Operador operador;
    public OrdemServico() {
        this.setDataAbertura(LocalDateTime.now());
        this.setPrioridade(Prioridade.BAIXA);
        this.setStatus(Status.ABERTO);
    }

    public OrdemServico(Integer id, Prioridade prioridade, String observacoes, Status status, Tecnico tecnico, Operador operador) {
        this.id = id;
        this.setDataAbertura(LocalDateTime.now());
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
        this.observacoes = observacoes;
        this.status = (status == null) ? 0 : status.getCod();
        this.tecnico = tecnico;
        this.operador = operador;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCod();
    }
    public Status getStatus() {
        return Status.toEnum(this.status);
    }
    public void setStatus(Status status) {
        this.status = status.getCod();
    }
}
