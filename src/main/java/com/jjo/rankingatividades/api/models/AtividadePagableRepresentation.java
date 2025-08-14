package com.jjo.rankingatividades.api.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjo.rankingatividades.domain.models.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representação paginada de uma atividade para listagens.
 *
 * Usada em endpoints com paginação (ex: GET /atividades?page=...).
 */
@Data
@NoArgsConstructor
public class AtividadePagableRepresentation {

    private Long id;
    private String descricao;
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;
}
