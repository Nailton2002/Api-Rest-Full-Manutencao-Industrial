package com.manutencao.industrial.domain.tecnico.repository;

import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    @Query("SELECT t FROM Tecnico t WHERE t.cpf =:cpf")
    Tecnico findByCPF(@Param("cpf") String cpf);
}
