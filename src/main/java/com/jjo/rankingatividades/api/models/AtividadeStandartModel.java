package com.jjo.rankingatividades.api.models;

import com.jjo.rankingatividades.domain.models.Status;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtividadeStandartModel {

    private Long id ;
    private String descricao ;
    private Status status ;
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;


}
