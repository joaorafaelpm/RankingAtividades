package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO auxiliar utilizado para associar uma atividade a um aluno existente.
 * Contém apenas o ID do aluno, que será utilizado na criação da atividade.
 */
@AllArgsConstructor
@Getter
@Setter
public class AlunoId {

    @NotNull // ID não pode ser nulo
    private Long id;
}
