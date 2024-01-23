package com.manutencao.industrial.domain.service.os;

import com.manutencao.industrial.domain.dto.os.resquest.OsForm;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpForm;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.service.operador.OperadorServiceImpl;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.repository.os.OsRepository;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.service.tecnico.TecnicoServiceImpl;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsServiceImpl {

    @Autowired
    private OsRepository repository;

    @Autowired
    private TecnicoServiceImpl tecnicoServiceImpl;

    @Autowired
    private OperadorServiceImpl operadorServiceImpl;

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

        Tecnico tec = tecnicoServiceImpl.findById(form.getTecnico());
        Operador ope = operadorServiceImpl.findById(form.getOperador());

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

        Tecnico tec = tecnicoServiceImpl.findById(form.getTecnico());

        obj.setTecnico(tec);

        return repository.save(obj);
    }


}
