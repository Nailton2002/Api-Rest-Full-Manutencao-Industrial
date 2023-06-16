package com.manutencao.industrial.domain.funcionario.repository;

import com.manutencao.industrial.domain.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("SELECT f FROM Funcionario f WHERE f.cpf =:cpf")
    Funcionario findByCPF(@Param("cpf") String cpf);
}
