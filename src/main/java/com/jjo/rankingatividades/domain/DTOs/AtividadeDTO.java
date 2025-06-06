package com.jjo.rankingatividades.domain.DTOs;

import java.time.OffsetDateTime;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class AtividadeDTO {
    
    @Valid
    @NotNull
    private AlunoId alunoId ;

    @NotBlank
    @Size(max=1000)
    private String descricao ;

}
