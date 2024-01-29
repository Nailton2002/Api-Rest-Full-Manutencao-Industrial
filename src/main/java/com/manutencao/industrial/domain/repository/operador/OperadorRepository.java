package com.manutencao.industrial.domain.repository.operador;

import com.manutencao.industrial.domain.entity.operador.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperadorRepository extends JpaRepository<Operador, Integer> {

    @Query("SELECT o FROM Operador o WHERE UPPER(o.nome) LIKE UPPER(CONCAT('%', :nome, '%'))")
    List<Operador> findByNome(String nome);

    //    @Query("SELECT o FROM Operador o WHERE o.cpf =:cpf")
    @Query("SELECT o FROM Operador o WHERE LOWER(o.cpf) LIKE LOWER(CONCAT('%', :cpf, '%'))")
    List<Operador> findByCPF(String cpf);

    boolean existsByTelefone(String fone);

    boolean existsByNome(String nome);

    boolean existsByCpf(String cpf);


}
