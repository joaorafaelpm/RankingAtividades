package com.jjo.rankingatividades.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Representação paginada de um aluno para respostas de listagem.
 *
 * Usada geralmente em endpoints com paginação (ex: GET /alunos?page=...).
 * Contém apenas as informações essenciais de um aluno.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPagableRepresentation {

    private Long id;
    private String name;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String curso;
    private String classe;
}
