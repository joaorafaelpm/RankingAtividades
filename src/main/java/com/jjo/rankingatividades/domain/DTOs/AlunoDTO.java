package com.jjo.rankingatividades.domain.DTOs;

import java.time.OffsetDateTime;

import org.hibernate.annotations.ValueGenerationType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO {

    @NotBlank
    private String name ;

    @Email
    @NotBlank
    private String email ;

    private OffsetDateTime dataNascimento ;

    @NotBlank
    private String curso ;


    @NotBlank
    private String classe ;

    public AlunoDTO(String name,String email, OffsetDateTime dataNascimento,  String curso, String classe) {
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.classe = classe;
    }



}
