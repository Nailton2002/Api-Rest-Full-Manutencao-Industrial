package com.manutencao.industrial.domain.service.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorForm;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpForm;
import com.manutencao.industrial.domain.dto.operador.response.OperadorListView;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.repository.funcionario.FuncionarioRepository;
import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.repository.operador.OperadorRepository;
import com.manutencao.industrial.infra.validation.ObjectNotFoundException;
import com.manutencao.industrial.infra.validation.ObjectNotFoundExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperadorServiceImpl {
    @Autowired
    private OperadorRepository repository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Operador create(OperadorForm form) {
        if (repository.existsByCpf(form.getCpf())) {
            throw new ObjectNotFoundExceptionService("CPF já cadastrado na base de dados!");
        }
        if (repository.existsByTelefone(form.getTelefone())){
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        var obj = new Operador(form);
        return repository.save(new Operador(form));
    }

    public List<OperadorListView> findAll() {
        List<Operador> listObj = repository.findAll();
        List<OperadorListView> listViews = listObj.stream().map(o -> new OperadorListView(o)).collect(Collectors.toList());
        if (listViews == null){
            throw new ObjectNotFoundException("Não existe Operador cadastrado!");
        }
        return listViews;
    }

    public Operador findById(Integer id) {
        var obj = repository.findById(id).orElseThrow(()-> new ObjectNotFoundExceptionService(
       "Este id = " + id + ", não existe no banco de dados!"));
        return obj;
    }

    public Operador update(Integer id, @Valid OperadorUpForm upForm) {
        var obj = findById(id);
        obj.atualizarOperador(upForm);
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
