package com.manutencao.industrial.domain.service.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.repository.funcionario.FuncionarioRepository;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.repository.tecnico.TecnicoRepository;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository repository;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public Tecnico create(TecnicoRequest request) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        if (repository.existsByTelefone(request.getTelefone())) {
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        var obj = new Tecnico(request);
        return repository.save(new Tecnico(request));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Page<Tecnico> findAllByPage(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public List<Tecnico> findByNome(String nome) {
        List<Tecnico> list = repository.findByNome(nome);
        return list;
    }

    public List<Tecnico> findByCpf(String cpf){
        List<Tecnico> listByCPF = repository.findByCPF(cpf);
        return listByCPF;
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id));
    }

    @Transactional
    public Tecnico update(Integer id, @Valid TecnicoUpRequest upRequest) {
        Tecnico tecnico = new Tecnico();
        tecnico = findById(id);
        tecnico.atualizarTecnico(upRequest);
        return repository.save(tecnico);
    }

    @Transactional
    public void delete(Integer id) {
        var obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new ObjectNotFoundExceptionService("Técnico possui Orden de Serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Funcionario findByCPF(TecnicoRequest view) {
        Funcionario obj = funcionarioRepository.findByCPF(view.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }


}
