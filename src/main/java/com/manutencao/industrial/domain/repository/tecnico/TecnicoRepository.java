package com.manutencao.industrial.domain.repository.tecnico;

import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    @Query("SELECT t FROM Tecnico t WHERE UPPER(t.nome) LIKE UPPER(CONCAT('%', :nome, '%'))")
    List<Tecnico> findByNome(@Param("nome") String nome);

    @Query("SELECT p FROM Tecnico p WHERE LOWER(p.nome) LIKE LOWER(:nome)")
    List<Tecnico> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT t FROM Tecnico t WHERE t.cpf =:cpf")
    List<Tecnico> findByCPF(String cpf);

    boolean existsByTelefone(String fone);

    boolean existsByNome(String nome);

    boolean existsByCpf(String cpf);
}
