package com.manutencao.industrial.domain.tecnico.service;

import com.manutencao.industrial.application.tecnico.dto.form.TecnicoForm;
import com.manutencao.industrial.application.tecnico.dto.form.TecnicoUpForm;
import com.manutencao.industrial.application.tecnico.dto.view.TecnicoListView;
import com.manutencao.industrial.application.tecnico.dto.view.TecnicoView;
import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import com.manutencao.industrial.domain.funcionario.repository.FuncionarioRepository;
import com.manutencao.industrial.domain.os.entity.OrdemServico;
import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import com.manutencao.industrial.domain.tecnico.repository.TecnicoRepository;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundException;
import com.manutencao.industrial.infra.exception.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tecnico create(TecnicoForm form) {
        if (findByCPF(form) != null) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        var obj = new Tecnico(form);
        return repository.save(new Tecnico(form));
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id ));

    }
    public TecnicoView buscarPorId(Integer id) {
        var obj = repository.findById(id).orElseThrow(()-> new ObjectNotFoundExceptionService("id não existe"));
        return new TecnicoView(obj);
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public List<TecnicoListView> buscarTodos(){
        List<Tecnico> list = repository.findAll();
        List<TecnicoListView> dados = list.stream().map(b -> new TecnicoListView(b)).collect(Collectors.toList());
        return dados;
    }

    public Tecnico update(Integer id, @Valid TecnicoUpForm upForm) {
        var obj = findById(id);
        obj.atualizarTecnico(upForm);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        var obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new ObjectNotFoundExceptionService("Técnico possui Ordens de Serviço, não pode ser deletado!");
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
