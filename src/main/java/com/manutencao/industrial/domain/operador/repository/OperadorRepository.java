package com.manutencao.industrial.domain.operador.repository;

import com.manutencao.industrial.domain.operador.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperadorRepository extends JpaRepository<Operador, Integer> {
}
