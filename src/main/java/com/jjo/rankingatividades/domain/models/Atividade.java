package com.jjo.rankingatividades.domain.models;


import java.time.OffsetDateTime;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name="atividades")
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id ;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn(name="id_aluno")
    private Aluno aluno ;

    private String descricao ;
    @Column(name = "data_inicio")
    private OffsetDateTime dataInicio;
    @Enumerated(EnumType.STRING)
    private Status status ;
    @Column(name = "data_fim")
    private OffsetDateTime dataFim;



    public Atividade(Long alunoId, String descricao ) {
        this.aluno = aluno;
        this.descricao = descricao ;
    }
    public Atividade(String descricao , Aluno aluno) {
        this.id = id;
        this.aluno = aluno;
        this.descricao = descricao ;
        this.dataInicio = OffsetDateTime.now();
        this.status = Status.PENDENTE;
        this.dataFim = null;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", aluno=" + aluno +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", status=" + status +
                ", dataFim=" + dataFim +
                '}';
    }
}
