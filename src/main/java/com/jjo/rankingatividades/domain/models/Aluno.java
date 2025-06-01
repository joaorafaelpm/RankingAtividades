package com.jjo.rankingatividades.domain.models;

import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    public Aluno(Long id, String name, String email, OffsetDateTime dataNascimento, String curso, String classe) {
        super(id, name, email, dataNascimento);
        this.curso = curso;
        this.classe = classe;
    }
    public Aluno (String name, String email, OffsetDateTime dataNascimento, String curso, String classe) {
        super(name, email, dataNascimento);
        this.curso = curso;
        this.classe = classe;
    }

    public void adicionarAtividadeAluno(Atividade atividade ) {
        atividades.add(atividade);
        atividade.setAluno(this);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "curso='" + curso + '\'' +
                ", classe='" + classe + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", atividades=" + atividades +
                '}';
    }


}
