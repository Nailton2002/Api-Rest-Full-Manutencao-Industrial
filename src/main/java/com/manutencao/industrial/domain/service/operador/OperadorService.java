package com.manutencao.industrial.domain.service.operador;

import com.manutencao.industrial.domain.dto.operador.resquest.OperadorRequest;
import com.manutencao.industrial.domain.dto.operador.resquest.OperadorUpRequest;
import com.manutencao.industrial.domain.entity.operador.Operador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface OperadorService {

    Operador create(OperadorRequest form);

    List<Operador> findAll();

    Page<Operador> findAllByPage(Pageable paginacao);

    List<Operador> findByNome(String nome);

    List<Operador> findByCPF(String cpf);

    Operador findById(Integer id);

    Operador update(Integer id, @Valid OperadorUpRequest upRequest);

    void delete(Integer id);
}
