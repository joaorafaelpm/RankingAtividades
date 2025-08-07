package com.jjo.rankingatividades.api.models;

import java.time.OffsetDateTime;
import java.util.List;

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
    private OffsetDateTime dataNascimento ;
    private String curso ;
    private String classe ;
    private List<AtividadeStandartModel> atividades;


}
