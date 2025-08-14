package com.jjo.rankingatividades.api.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação detalhada de um aluno.
 *
 * Inclui dados pessoais, informações acadêmicas e lista de atividades relacionadas.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUniqueRepresentation {

    private Long id;
    private String name;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String curso;
    private String classe;

    private List<AtividadeStandartModel> atividades;
}
