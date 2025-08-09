package com.jjo.rankingatividades.domain.DTOs;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AlunoDTO {

    @NotBlank
    private String name ;

    @Email
    @NotBlank
    private String email ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento ;

    @NotBlank
    private String curso ;

    @NotBlank
    private String classe ;




}
