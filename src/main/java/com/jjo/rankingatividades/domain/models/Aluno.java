package com.jjo.rankingatividades.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data
@Table(name="alunos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{

    @NotBlank
    private String curso ;
    
    @NotBlank
    private String classe ;

    public Aluno () {
        super();
    }

    public void adicionarAtividadeAluno(Atividade atividade ) {
        atividades.add(atividade);
        atividade.setAluno(this);
    }


}
