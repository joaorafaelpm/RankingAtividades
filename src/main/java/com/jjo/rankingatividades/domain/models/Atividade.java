package com.jjo.rankingatividades.domain.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entidade que representa uma atividade atribuída a um aluno.
 *
 * Contém informações como descrição, status, datas de início e fim,
 * e está vinculada a um {@link Aluno}.
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Table(name = "atividades")
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn(name = "id_aluno") // Chave estrangeira para aluno
    private Aluno aluno;

    @Enumerated(EnumType.STRING) // Salva o nome da constante no banco
    private Status status;

    private String descricao;

    @Column(name = "data_inicio", columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @CreationTimestamp // Define automaticamente a data no momento da inserção
    private LocalDate dataInicio;

    @Column(name = "data_fim", columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;
}
