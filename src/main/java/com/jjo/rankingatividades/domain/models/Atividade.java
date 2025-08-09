package com.jjo.rankingatividades.domain.models;


import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
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

    @Enumerated(EnumType.STRING)
    private Status status ;

    private String descricao ;

    @Column(name = "data_inicio", columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreationTimestamp
    private LocalDate dataInicio;

    @Column(name = "data_fim" , columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;




}
