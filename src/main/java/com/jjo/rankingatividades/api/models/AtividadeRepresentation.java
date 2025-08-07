package com.jjo.rankingatividades.api.models;

import java.time.OffsetDateTime;

import com.jjo.rankingatividades.domain.models.Status;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class AtividadeRepresentation {


    private Long id ;
    private AlunoStandartModel aluno;
    private String descricao ;
    private OffsetDateTime dataInicio;
    private Status status ;
    private OffsetDateTime dataFim;

    public AtividadeRepresentation(Long id,AlunoStandartModel aluno , String descricao) {
        this.id = id;
        this.aluno = aluno ;
        this.descricao = descricao ;
        this.dataInicio = OffsetDateTime.now();
        this.status = Status.PENDENTE;
        this.dataFim = null;
    }

}

