package com.manutencao.industrial.domain.repository.funcionario;

import com.manutencao.industrial.domain.entity.funcionario.Funcionario;
import com.manutencao.industrial.domain.entity.tecnico.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT f FROM Funcionario f WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Tecnico> findByNome(String nome);

    @Query(value = "SELECT f FROM Funcionario f WHERE f.cpf LIKE %?1%")
    Tecnico findByCPF(@Param("cpf") String cpf);

}
