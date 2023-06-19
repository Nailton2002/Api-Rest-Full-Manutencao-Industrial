package com.manutencao.industrial.domain.os.service;

import com.manutencao.industrial.application.os.dto.form.OsForm;
import com.manutencao.industrial.application.os.dto.form.OsUpForm;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.operador.model.Operador;
import com.manutencao.industrial.domain.operador.service.OperadorService;
import com.manutencao.industrial.domain.os.entity.OrdemServico;
import com.manutencao.industrial.domain.os.repository.OsRepository;
import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import com.manutencao.industrial.domain.tecnico.service.TecnicoService;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundException;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OsRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private OperadorService operadorService;

    public OrdemServico create(@Valid OsForm form) {
        return fromOsForm(form);
    }

    public OrdemServico update(@Valid OsUpForm upForm) {
        findById(upForm.getId());
        return fromOsUpForm(upForm);
    }

    public void delete(Integer id) {
        var obj = findById(id);
        repository.deleteById(id);
    }

    public OrdemServico findById(Integer id) {
        Optional<OrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
    }

    public List<OrdemServico> findAll() {
        return repository.findAll();
    }

    private OrdemServico fromOsForm(OsForm form) {
        OrdemServico obj = new OrdemServico();
        obj.setId(form.getId());
        obj.setObservacoes(form.getObservacoes());
        obj.setPrioridade(Prioridade.toEnum(form.getPrioridade().getCod()));
        obj.setStatus(Status.toEnum(form.getStatus().getCod()));

        Tecnico tec = tecnicoService.findById(form.getTecnico());
        Operador ope = operadorService.findById(form.getOperador());

        obj.setTecnico(tec);
        obj.setOperador(ope);

        if(obj.getStatus().getCod().equals(2)) {
            obj.setDataFechamento(LocalDateTime.now());
        }
        return repository.save(obj);
    }

    private OrdemServico fromOsUpForm(OsUpForm form) {

        OrdemServico obj = new OrdemServico();
        obj.setId(form.getId());
        obj.setObservacoes(form.getObservacoes());
        obj.setPrioridade(Prioridade.toEnum(form.getPrioridade().getCod()));
        obj.setStatus(Status.toEnum(form.getStatus().getCod()));

        Tecnico tec = tecnicoService.findById(form.getTecnico());

        obj.setTecnico(tec);

        return repository.save(obj);
    }


}
