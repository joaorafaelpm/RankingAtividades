package com.jjo.rankingatividades.domain.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DescriptionDTO {

    @Size(max = 1000)
    @NotBlank
    private String descricao ;




}
