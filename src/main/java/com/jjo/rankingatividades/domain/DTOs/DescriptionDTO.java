package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilizado para atualizar a descrição de uma atividade existente.
 */
@Getter
@Setter
@AllArgsConstructor
public class DescriptionDTO {

    @Size(max = 1000) // Limite máximo para a descrição
    @NotBlank // Descrição obrigatória
    private String descricao;
}
