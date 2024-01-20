package com.manutencao.industrial.domain.service.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoListView;
import com.manutencao.industrial.domain.dto.tecnico.response.TecnicoView;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoForm;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpForm;
import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface TecnicoService {

    Tecnico create(TecnicoForm form);

    List<TecnicoListView> findAll();

    List<TecnicoView> findByNome(String nome);

    Page<Tecnico> findByPage(Pageable paginacao);

    Tecnico findById(Integer id);

    Tecnico update(Integer id, @Valid TecnicoUpForm upForm);

    void delete(Integer id);

}