package com.jjo.rankingatividades.api.models;

import lombok.*;

/**
 * Representação simplificada de um aluno.
 *
 * Usada como sub-representação em outros modelos, como dentro de uma {@link AtividadeUniqueRepresentation}.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlunoStandartModel {

    private Long id;
    private String name;
}
