package com.jjo.rankingatividades.domain.models;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;
import org.springframework.cglib.core.Local;


@Entity
@Data
@Table(name="alunos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{

    @Override
    public String toString() {
        return "Aluno{" +
                "curso='" + curso + '\'' +
                ", classe='" + classe + '\'' +
                ", atividades=" + atividades +
                ", dataNascimento=" + dataNascimento +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

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
