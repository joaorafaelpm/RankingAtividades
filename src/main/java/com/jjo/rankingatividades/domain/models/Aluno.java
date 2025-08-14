package com.jjo.rankingatividades.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Data;

/**
 * Entidade que representa um aluno no sistema.
 *
 * Extende a classe abstrata {@link Usuario}, herdando atributos comuns como nome, e-mail e data de nascimento.
 *
 * O aluno também possui informações acadêmicas (curso e classe)
 * e pode estar associado a várias atividades.
 */
@Entity
@Data
@Table(name = "alunos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança no mesmo esquema de tabela
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario {

    @NotBlank // Curso é obrigatório
    private String curso;

    @NotBlank // Classe é obrigatória
    private String classe;

    public Aluno() {
        super();
    }

    /**
     * Adiciona uma atividade ao aluno e mantém a relação bidirecional consistente.
     *
     * @param atividade Atividade a ser associada ao aluno
     */
    public void adicionarAtividadeAluno(Atividade atividade) {
        atividades.add(atividade);
        atividade.setAluno(this);
    }
}
