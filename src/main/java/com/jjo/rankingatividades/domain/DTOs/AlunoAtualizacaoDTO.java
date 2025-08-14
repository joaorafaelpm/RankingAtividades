package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO utilizado para atualizar informações de um aluno existente.
 * Contém apenas os campos que podem ser modificados após a criação.
 */
@Data
public class AlunoAtualizacaoDTO {

    @Email // Garante que o valor seja um e-mail válido
    @NotBlank // Não permite valor nulo ou string vazia
    private String email;

    @NotBlank // Curso não pode ser vazio
    private String curso;

    @NotBlank // Classe não pode ser vazia
    private String classe;
}
