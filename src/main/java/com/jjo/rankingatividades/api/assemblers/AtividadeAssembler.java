package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.services.AlunoService;

import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Atividade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class AtividadeAssembler {

    private final AlunoService alunoService ;

    public Atividade toEntity(AtividadeDTO atividadeDTO) {
        Aluno aluno = alunoService.procurarPeloId(atividadeDTO.getAlunoId().getId());
        return new Atividade(atividadeDTO.getDescricao() , aluno) ;
    }



}
