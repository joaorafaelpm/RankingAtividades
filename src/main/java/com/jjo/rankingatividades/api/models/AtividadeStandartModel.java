package com.jjo.rankingatividades.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjo.rankingatividades.domain.models.Status;
import lombok.*;

import java.time.LocalDate;

/**
 * Representação simplificada de uma atividade.
 *
 * Usada dentro de {@link AlunoUniqueRepresentation} para listar atividades do aluno.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtividadeStandartModel {

    private Long id;
    private String descricao;
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;
}
