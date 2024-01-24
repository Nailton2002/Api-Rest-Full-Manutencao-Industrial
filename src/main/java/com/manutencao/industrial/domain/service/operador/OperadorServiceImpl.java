package com.manutencao.industrial.domain.service.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.repository.funcionario.FuncionarioRepository;
import com.manutencao.industrial.domain.repository.operador.OperadorRepository;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class OperadorServiceImpl implements OperadorService {
    @Autowired
    private OperadorRepository repository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Operador create(OperadorRequest request) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        if (repository.existsByTelefone(request.getTelefone())) {
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        Operador obj = new Operador(request);
        return repository.save(new Operador(request));
    }

    public List<Operador> findAll() { return repository.findAll(); }

    public Page<Operador> findAllByPage(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public List<Operador> findByNome(String nome) {
        List<Operador> list = repository.findByNome(nome);
        return list;
    }

    public Operador findById(Integer id) {
        Optional<Operador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id));
    }

    @Transactional
    public Operador update(Integer id, @Valid OperadorUpRequest upRequest) {
        var obj = findById(id);
        obj.atualizarOperador(upRequest);
        return repository.save(obj);
    }

    @Transactional
    public void delete(Integer id) {
        Operador obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new ObjectNotFoundExceptionService("Funcionario possui OS, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Funcionario findByCPF(OperadorRequest request) {
        Funcionario obj = funcionarioRepository.findByCPF(request.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }

}
