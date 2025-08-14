package com.jjo.rankingatividades.domain.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Classe abstrata que serve como base para todas as entidades que representam usuários no sistema.
 *
 * Possui atributos comuns como ID, nome, e-mail e data de nascimento.
 * Mantém também a relação com as atividades do usuário.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id;

    @NotBlank
    protected String name;

    @NotBlank
    @Email
    protected String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected LocalDate dataNascimento;

    /**
     * Lista de atividades associadas ao usuário.
     * mappedBy = "aluno" indica que a relação é mapeada pelo atributo "aluno" em {@link Atividade}.
     * cascade = CascadeType.ALL garante que mudanças sejam propagadas para as atividades.
     * orphanRemoval = true remove atividades órfãs automaticamente.
     */
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Atividade> atividades = new ArrayList<>();

    public Usuario(String name, String email, LocalDate dataNascimento) {
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Usuario(Long id, String name, String email, LocalDate dataNascimento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
}
