package com.manutencao.industrial.domain.service.os;

import com.manutencao.industrial.domain.dto.os.resquest.OsRequest;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.entity.os.OrdemServico;

import javax.validation.Valid;
import java.util.List;

public interface OsService {

    OrdemServico create(@Valid OsRequest request);

    OrdemServico update(@Valid OsUpRequest upRequest);

    void delete(Integer id);

    void finalizarOS(Integer id);

    OrdemServico findById(Integer id);

    List<OrdemServico> findAll();
}
