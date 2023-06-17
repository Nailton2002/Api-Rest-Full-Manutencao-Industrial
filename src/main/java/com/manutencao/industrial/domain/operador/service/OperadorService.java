package com.manutencao.industrial.domain.operador.service;

import com.manutencao.industrial.application.operador.dto.form.OperadorForm;
import com.manutencao.industrial.application.operador.dto.form.OperadorUpForm;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.funcionario.repository.FuncionarioRepository;
import com.manutencao.industrial.domain.operador.model.Operador;
import com.manutencao.industrial.domain.operador.repository.OperadorRepository;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundException;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundExceptionService;
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
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Operador.class.getName()));
    }

    public List<Operador> findAll() {
        return repository.findAll();
    }

    public Operador create(OperadorForm form) {
        if (findByCPF(form) != null) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Operador(null, form.getNome(), form.getCpf(), form.getTelefone()));
    }

    public Operador update(Integer id, @Valid OperadorUpForm upForm) {
        Operador obj = findById(id);
        obj.setNome(upForm.getNome());
        obj.setTelefone(upForm.getTelefone());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        Operador obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new ObjectNotFoundExceptionService("Funcionario possui OS, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Funcionario findByCPF(OperadorForm form) {
        Funcionario obj = funcionarioRepository.findByCPF(form.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }

}
