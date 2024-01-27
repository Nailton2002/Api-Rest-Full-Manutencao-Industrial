package com.manutencao.industrial.domain.service.os;

import com.manutencao.industrial.domain.dto.os.resquest.OsFinalizada;
import com.manutencao.industrial.domain.dto.os.resquest.OsRequest;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import com.manutencao.industrial.domain.repository.os.OsRepository;
import com.manutencao.industrial.domain.service.operador.OperadorService;
import com.manutencao.industrial.domain.service.tecnico.TecnicoService;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OsServiceImpl implements OsService {

    private final OsRepository repository;

    private final TecnicoService tecnicoServiceImpl;

    private final OperadorService operadorServiceImpl;

    @Transactional
    public OrdemServico create(OsRequest request) {
        return fromEntityToRequest(request);
    }

    @Transactional
    public OrdemServico update(OsUpRequest upRequest) {
        findById(upRequest.getId());
        return fromEntityToUpRequest(upRequest);
    }

    @Transactional
    public OrdemServico finalizarOS(OsFinalizada upRequest) {
        findById(upRequest.getId());
        return finalizandoOS(upRequest);
    }

    @Transactional
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

    private OrdemServico fromEntityToRequest(OsRequest request) {
        OrdemServico obj = new OrdemServico();
        obj.setId(request.getId());
        obj.setObservacoes(request.getObservacoes());
        obj.setStatus(Status.toEnum(request.getStatus().getCod()));
        obj.setPrioridade(Prioridade.toEnum(request.getPrioridade().getCod()));

        Tecnico tec = tecnicoServiceImpl.findById(request.getTecnico());
        Operador ope = operadorServiceImpl.findById(request.getOperador());

        obj.setTecnico(tec);
        obj.setOperador(ope);

        if (obj.getStatus().getCod().equals(2)) {
            obj.setDataFechamento(LocalDateTime.now());
        }
        return repository.save(obj);
    }

    private OrdemServico fromEntityToUpRequest(OsUpRequest upRequest) {
        OrdemServico obj = new OrdemServico();
        obj.setId(upRequest.getId());
        obj.setObservacoes(upRequest.getObservacoes());
        obj.setStatus(Status.toEnum(upRequest.getStatus().getCod()));
        obj.setPrioridade(Prioridade.toEnum(upRequest.getPrioridade().getCod()));

        Tecnico tec = tecnicoServiceImpl.findById(upRequest.getTecnico());
        obj.setTecnico(tec);
        Operador ope = operadorServiceImpl.findById(upRequest.getOperador());
        obj.setOperador(ope);

        if (obj.getStatus().getCod().equals(2)) {
            obj.setDataFechamento(LocalDateTime.now());
        }
        return repository.save(obj);
    }

    private OrdemServico finalizandoOS(OsFinalizada dto) {
        OrdemServico obj = findById(dto.getId());
        obj.setStatus(Status.toEnum(dto.getStatus().getCod()));
        if (obj.getStatus().getCod().equals(2)) {
            obj.setDataFechamento(LocalDateTime.now());
        }
        return obj;
    }
}

