package com.manutencao.industrial.domain.repository.os;

import com.manutencao.industrial.domain.entity.os.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<OrdemServico, Integer> {
}
