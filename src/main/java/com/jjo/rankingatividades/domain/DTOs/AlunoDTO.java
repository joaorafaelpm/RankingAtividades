package com.jjo.rankingatividades.domain.DTOs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO utilizado para cadastrar um novo aluno no sistema.
 * Inclui todos os dados obrigatórios necessários para a criação.
 */
@Data
public class AlunoDTO {

    @NotBlank // Nome não pode ser vazio
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") // Formata a data no padrão dia-mês-ano
    private LocalDate dataNascimento;

    @NotBlank
    private String curso;

    @NotBlank
    private String classe;
}
