package com.manutencao.industrial.domain.dto.os.response;

import com.manutencao.industrial.domain.entity.os.OrdemServico;

import javax.validation.constraints.NotEmpty;

public class OsListView {

    private Integer id;
    @NotEmpty(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    private Integer tecnico;
    private Integer operador;

    public OsListView() { }

    public OsListView(OrdemServico obj) {
        this.id = obj.getId();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.operador = obj.getOperador().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getOperador() {
        return operador;
    }

    public void setOperador(Integer operador) {
        this.operador = operador;
    }
}
