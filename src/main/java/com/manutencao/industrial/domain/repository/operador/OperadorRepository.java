package com.manutencao.industrial.domain.repository.operador;

import com.manutencao.industrial.domain.entity.operador.Operador;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperadorRepository extends JpaRepository<Operador, Integer> {

    @Query("SELECT o FROM Operador o WHERE LOWER(o.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Tecnico> findByNome(String nome);

    @Query("SELECT o FROM Operador o WHERE o.cpf =:cpf")
    Tecnico findByCPF(@Param("cpf") String cpf);

    boolean existsByTelefone(String fone);

    boolean existsByNome(String nome);

    boolean existsByCpf(String cpf);


}
