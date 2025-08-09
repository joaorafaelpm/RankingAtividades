package com.jjo.rankingatividades.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjo.rankingatividades.domain.models.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class AtividadeUniqueRepresentation {

        private Long id ;
        private AlunoStandartModel aluno;
        private String descricao ;
        private Status status ;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

        private LocalDate dataInicio;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate dataFim;

}
