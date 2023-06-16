package com.manutencao.industrial.domain.tecnico.service;

import com.manutencao.industrial.application.tecnico.dto.form.TecnicoForm;
import com.manutencao.industrial.application.tecnico.dto.form.TecnicoUpForm;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.funcionario.repository.FuncionarioRepository;
import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import com.manutencao.industrial.domain.tecnico.repository.TecnicoRepository;
import com.manutencao.industrial.infra.exception.DataIntegratyViolationException;
import com.manutencao.industrial.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoForm form) {
        if (findByCPF(form) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        Tecnico newTec = new Tecnico(null, form.getNome(), form.getCpf(), form.getTelefone());
        return repository.save(newTec);
    }

    public Tecnico update(Integer id, @Valid TecnicoUpForm upForm) {
        var obj = findById(id);
        obj.setNome(upForm.getNome());
        obj.setTelefone(upForm.getTelefone());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        var obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private Funcionario findByCPF(TecnicoForm view) {
        Funcionario obj = funcionarioRepository.findByCPF(view.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }
}
