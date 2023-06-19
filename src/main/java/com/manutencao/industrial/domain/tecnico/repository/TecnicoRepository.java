package com.manutencao.industrial.domain.tecnico.repository;

import com.manutencao.industrial.domain.tecnico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    @Query("SELECT t FROM Tecnico t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Tecnico> findByNome(String nome);

    @Query("SELECT t FROM Tecnico t WHERE t.cpf =:cpf")
    Tecnico findByCPF(@Param("cpf") String cpf);

    boolean existsByTelefone(String fone);

    boolean existsByNome(String nome);

    boolean existsByCpf(String cpf);
}
