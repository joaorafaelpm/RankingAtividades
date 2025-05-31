package com.jjo.rankingatividades.models;

import java.time.OffsetDateTime;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Status;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

