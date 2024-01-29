package com.manutencao.industrial.domain.service.os;

import com.manutencao.industrial.domain.dto.os.resquest.OsFinalizada;
import com.manutencao.industrial.domain.dto.os.resquest.OsRequest;
import com.manutencao.industrial.domain.dto.os.resquest.OsUpRequest;
import com.manutencao.industrial.domain.entity.os.OrdemServico;

import java.util.List;

public interface OsService {

    OrdemServico create(OsRequest request);

//    OrdemServico update(OsUpRequest upRequest);

    OrdemServico update(Integer id, OsUpRequest upRequest);

    void delete(Integer id);

    OrdemServico finalizarOS(OsFinalizada upRequest) ;

//    OrdemServico getReferenceById(Integer id);

    OrdemServico findById(Integer id);

    List<OrdemServico> findAll();
}
