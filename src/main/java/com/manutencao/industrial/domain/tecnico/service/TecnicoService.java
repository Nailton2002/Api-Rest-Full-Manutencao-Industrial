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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Tecnico create(TecnicoForm form) {
        if (repository.existsByCpf(form.getCpf())) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        if (repository.existsByTelefone(form.getTelefone())){
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        var obj = new Tecnico(form);
        return repository.save(new Tecnico(form));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public List<TecnicoView> findByNome(String nome){
        List<Tecnico> list = repository.findByNome(nome);
        List<TecnicoView> listViews = list.stream().map(b -> new TecnicoView(b)).collect(Collectors.toList());
        return listViews;
    }

    public Page<Tecnico> findByPage(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id ));

    }

    public Tecnico update(Integer id, @Valid TecnicoUpForm upForm) {
        var obj = findById(id);
        obj.atualizarTecnico(upForm);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        var obj = findById(id);
        if (obj.getList().size() > 0) {
            throw new ObjectNotFoundExceptionService("Técnico possui Orden de Serviço, não pode ser deletado!");
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
