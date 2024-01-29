package com.manutencao.industrial.domain.entity.os;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manutencao.industrial.domain.dto.os.resquest.OsFinalizada;
import com.manutencao.industrial.domain.dto.os.resquest.OsRequest;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    @JoinColumn(name = "operador_id")
    private Operador operador;
    private Integer prioridade;
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    
    //OBS: Este metodo do dto para entidade, pode ser feito na propria classe do jeito que fiz e ate mesmo
    //     de uma outra forma, que é feita na classe service, no final eles tem a mesma finalidade...
    public OrdemServico(OsRequest request) {
        this.id = request.getId();
        this.observacoes = request.getObservacoes();
        this.status = request.getStatus().getCod();
        this.prioridade = request.getPrioridade().getCod();

        Tecnico tecnico = new Tecnico();
        tecnico.setId(request.getTecnico());
        this.setTecnico(tecnico);

        Operador operador = new Operador();
        operador.setId(request.getOperador());
        this.setOperador(operador);

        if (this.getStatus().getCod().equals(2) && request.getStatus().getCod().equals(2)) {
            request.setDataFechamento(LocalDateTime.now());
        }
    }

    //OBS: Este metodo de atualizar que recebe um dto como parametro, pode ser feito na propria classe do jeito que
    //     fiz e ate mesmo de uma outra forma, que é feita na classe service, no final eles tem a mesma finalidade...
    public void atualizarTecnico(OsUpRequest upRequest) {
        if (upRequest.getId() != null) {
            this.setId(upRequest.getId());
        }
        if (upRequest.getStatus() != null && upRequest.getStatus().getCod() != null) {
            this.setStatus(upRequest.getStatus());
        }
        if (upRequest.getTecnico() != null) {
            Tecnico tecnico = new Tecnico();
            tecnico.setId(upRequest.getTecnico());
            this.setTecnico(tecnico);
        }
        if (upRequest.getOperador() != null) {
            Operador operador = new Operador();
            operador.setId(upRequest.getOperador());
            this.setOperador(operador);
        }
        if (upRequest.getPrioridade() != null && upRequest.getPrioridade().getCod() != null) {
            this.setPrioridade(upRequest.getPrioridade());
        }
        if (upRequest.getObservacoes() != null) {
            this.setObservacoes(upRequest.getObservacoes());
        }
    }

    public OrdemServico(OsFinalizada request) {
        this.id = request.getId();
        this.status = request.getStatus().getCod();
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
        this.operador = operador;
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
