package com.manutencao.industrial.domain.operador.service;

import com.manutencao.industrial.application.operador.dto.view.OperadorDTO;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.funcionario.repository.FuncionarioRepository;
import com.manutencao.industrial.domain.operador.model.Operador;
import com.manutencao.industrial.domain.operador.repository.OperadorRepository;
import com.manutencao.industrial.infra.exception.DataIntegratyViolationException;
import com.manutencao.industrial.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {
    @Autowired
    private OperadorRepository repository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Operador findById(Integer id) {
        Optional<Operador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Operador.class.getName()));
    }

    public List<Operador> findAll() {
        return repository.findAll();
    }

    public Operador create(OperadorDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Operador(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Operador update(Integer id, @Valid OperadorDTO objDTO) {
        Operador oldObj = findById(id);

        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Operador obj = findById(id);

        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Pessoa possui Ordens de Serviço, não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private Funcionario findByCPF(OperadorDTO objDTO) {
        Funcionario obj = funcionarioRepository.findByCPF(objDTO.getCpf());

        if (obj != null) {
            return obj;
        }
        return null;
    }

}
