package com.jjo.rankingatividades.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jjo.rankingatividades.domain.models.Atividade;

import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRepresentation {
    
    private Long id ;
    private String name ;
    private String email ;
    private OffsetDateTime dataNascimento ;
    private String curso ;
    private String classe ;
    private List<AtividadeStandartModel> atividades;

    @Override
    public String toString() {
        return "AlunoRepresentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", curso='" + curso + '\'' +
                ", classe='" + classe + '\'' +
                ", atividades=" + atividades +
                '}';
    }

}
