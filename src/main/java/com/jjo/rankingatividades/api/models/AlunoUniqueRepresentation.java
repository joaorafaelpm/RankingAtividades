package com.jjo.rankingatividades.api.models;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUniqueRepresentation {
    
    private Long id ;
    private String name ;
    private String email ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento ;
    private String curso ;
    private String classe ;
    private List<AtividadeStandartModel> atividades;


}
