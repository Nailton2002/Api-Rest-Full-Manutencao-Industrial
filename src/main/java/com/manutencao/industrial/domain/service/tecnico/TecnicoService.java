package com.manutencao.industrial.domain.service.tecnico;

import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoRequest;
import com.manutencao.industrial.domain.dto.tecnico.resquest.TecnicoUpRequest;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface TecnicoService {

    Tecnico create(TecnicoRequest form);

    List<Tecnico> findAll();

    List<Tecnico> findByNome(String nome);

    Page<Tecnico> findAllByPage(Pageable paginacao);

    Tecnico findById(Integer id);

    Tecnico update(Integer id, @Valid TecnicoUpRequest upForm);

    void delete(Integer id);

}
