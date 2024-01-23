package com.manutencao.industrial.domain.entity.funcionario;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity(name = "Funcionario")
@Table(name = "tb_funcionario")
@EqualsAndHashCode(of = "id, cpf")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
}


//    public Funcionario() {
//    }
//
//    public Funcionario(Integer id, String nome, String cpf, String telefone) {
//        this.id = id;
//        this.nome = nome;
//        this.cpf = cpf;
//        this.telefone = telefone;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Funcionario other = (Funcionario) obj;
//        if (cpf == null) {
//            if (other.cpf != null)
//                return false;
//        } else if (!cpf.equals(other.cpf))
//            return false;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        return true;
//    }
//}
