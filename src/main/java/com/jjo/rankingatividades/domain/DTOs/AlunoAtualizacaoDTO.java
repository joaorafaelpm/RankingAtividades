package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AlunoAtualizacaoDTO {

    @Email
    @NotBlank
    private String email ;

    @NotBlank
    private String curso ;

    @NotBlank
    private String classe ;

}
