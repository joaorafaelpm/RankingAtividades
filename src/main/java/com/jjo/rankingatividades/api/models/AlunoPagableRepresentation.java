package com.jjo.rankingatividades.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPagableRepresentation {

    private Long id ;
    private String name ;
    private String email ;
    private OffsetDateTime dataNascimento ;
    private String curso ;
    private String classe ;

}
