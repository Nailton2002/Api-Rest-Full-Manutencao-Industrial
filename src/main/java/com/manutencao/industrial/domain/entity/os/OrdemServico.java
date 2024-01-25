package com.manutencao.industrial.domain.entity.os;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "OrdemServico")
@Table(name = "tb_ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Operador operador;
    private Integer prioridade;
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;

    public String finalizarOS() {
        if (dataFechamento.isEqual(LocalDateTime.now()) && status.equals(Status.ENCERRADO)) {
            return "nao pode";
        } else {
            dataFechamento = LocalDateTime.now();
            setStatus(Status.ENCERRADO);
        }
        return "OS finalizadas com sucesso";
    }

    public OrdemServico() {
        this.setStatus(Status.ABERTO);
        this.setPrioridade(Prioridade.BAIXA);
        this.setDataAbertura(LocalDateTime.now());
    }

    public OrdemServico(Integer id, Prioridade prioridade, Status status, String observacoes, Tecnico tecnico, Operador operador) {
        this.id = id;
        this.status = (status == null) ? 0 : status.getCod();
        this.tecnico = tecnico;
        this.operador = this.operador;
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
        this.observacoes = observacoes;
        this.setDataAbertura(LocalDateTime.now());
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
