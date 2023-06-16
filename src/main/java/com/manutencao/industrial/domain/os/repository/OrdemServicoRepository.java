package com.manutencao.industrial.domain.os.repository;

import com.manutencao.industrial.domain.os.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
}
