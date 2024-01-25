package com.manutencao.industrial.domain.service.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.repository.funcionario.FuncionarioRepository;
import com.manutencao.industrial.domain.repository.operador.OperadorRepository;
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
public class OperadorServiceImpl implements OperadorService {

    private final OperadorRepository repository;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public Operador create(OperadorRequest request) {
        if (repository.existsByCpf(request.getCpf())) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        if (repository.existsByTelefone(request.getTelefone())) {
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        Operador obj = new Operador(request);
        repository.save(obj);
        return new Operador(request);
    }

    public List<Operador> findAll() { return repository.findAll(); }

    public Page<Operador> findAllByPage(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public List<Operador> findByNome(String nome) {
        List<Operador> listbyNome = repository.findByNome(nome);
        return listbyNome;
    }

    public List<Operador> findByCPF(String cpf){
        List<Operador> listPorCpf = repository.findByCPF(cpf);
        return listPorCpf;
    }

    public Operador findById(Integer id) {
        Optional<Operador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id));
    }

    @Transactional
    public Operador update(Integer id, @Valid OperadorUpRequest upRequest) {
        Operador objId = findById(id);
        objId.atualizarOperador(upRequest);
        return repository.save(objId);
    }

    @Transactional
    public void delete(Integer id) {
        // Encontrar o objeto Operador pelo seu id
        Operador obj = findById(id);
        // Verificar se o objeto Operador tem uma lista não vazia
        if (obj.getList().size() > 0) {
            // Lançar uma exceção se a lista não estiver vazia
            throw new ObjectNotFoundExceptionService("Funcionario possui OS, não pode ser deletado!");
        }
        // Se a lista estiver vazia, deletar o objeto Operador pelo id
        repository.deleteById(id);
    }


}
