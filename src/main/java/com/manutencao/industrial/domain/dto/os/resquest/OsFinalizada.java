package com.manutencao.industrial.domain.dto.os.resquest;

import com.manutencao.industrial.domain.entity.os.OrdemServico;
import com.manutencao.industrial.domain.enums.Prioridade;
import com.manutencao.industrial.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsFinalizada {

    private Integer id;
    private Integer status;

    public OsFinalizada  (OrdemServico obj){
        this.id = obj.getId();
        this.status = obj.getStatus().getCod();
    }
    public Status getStatus() { return Status.toEnum(this.status); }

    public void setStatus(Integer status) { this.status = status; }
}
