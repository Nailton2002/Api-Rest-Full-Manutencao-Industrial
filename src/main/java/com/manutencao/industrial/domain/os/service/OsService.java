package com.manutencao.industrial.domain.os.service;

import com.manutencao.industrial.application.os.dto.view.OSDTO;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.operador.model.Operador;
import com.manutencao.industrial.domain.operador.service.OperadorService;
import com.manutencao.industrial.domain.os.entity.OrdemServico;
import com.manutencao.industrial.domain.os.repository.OsRepository;
import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import com.manutencao.industrial.domain.tecnico.service.TecnicoService;
import com.manutencao.industrial.infra.exception.ObjectNotFoundException;
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

    public OrdemServico findById(Integer id) {
        Optional<OrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
    }

    public List<OrdemServico> findAll() {
        return repository.findAll();
    }

    public OrdemServico create(@Valid OSDTO obj) {
        return fromDTO(obj);
    }

    public OrdemServico update(@Valid OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    private OrdemServico fromDTO(OSDTO obj) {
        OrdemServico newObj = new OrdemServico();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
        newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Operador ope = operadorService.findById(obj.getOperador());

        newObj.setTecnico(tec);
        newObj.setOperador(ope);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(newObj);
    }
}
