package com.jjo.rankingatividades.models;

import com.jjo.rankingatividades.domain.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AtividadeStandartModel {

    private Long id ;
    private String descricao ;
    private Status status ;
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;


}
