package com.manutencao.industrial.domain.service.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoView;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.repository.funcionario.FuncionarioRepository;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import com.manutencao.industrial.domain.repository.tecnico.TecnicoRepository;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoServiceImpl implements TecnicoService{

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
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

    public List<Tecnico> findAll(){ return repository.findAll(); }

    public List<Tecnico> findByNome(String nome){
        List<Tecnico> list = repository.findByNome(nome);
        return list;
    }

    public Page<Tecnico> findByPage(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptionService("Objeto não encontrado! Id: " + id ));

    }

    @Transactional
    public Tecnico update(Integer id, @Valid TecnicoUpForm upForm) {
        var obj = findById(id);
        obj.atualizarTecnico(upForm);
        return repository.save(obj);
    }

    @Transactional
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
