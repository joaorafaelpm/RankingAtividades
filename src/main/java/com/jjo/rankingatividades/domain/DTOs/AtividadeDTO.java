package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilizado para cadastrar uma nova atividade.
 * Contém o ID do aluno associado e a descrição da atividade.
 */
@Getter
@Setter
public class AtividadeDTO {

    @Valid // Garante que a validação seja aplicada ao objeto AlunoId
    @NotNull // Não pode ser nulo, pois a atividade precisa estar vinculada a um aluno
    private AlunoId alunoId;

    @NotBlank
    @Size(max = 1000) // Limite de caracteres da descrição
    private String descricao;
}
