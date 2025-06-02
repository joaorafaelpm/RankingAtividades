package com.jjo.rankingatividades.assemblers;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.models.AlunoRepresentation;
import com.jjo.rankingatividades.models.AtividadeRepresentation;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtividadeMapper {

    @Bean
    Atividade atividadeDTOToAtividade (AtividadeDTO atividadeDTO);
    @Bean
    AtividadeRepresentation atividadeToAtividadeRepresentation (Atividade atividade);

    @Bean
    List<AtividadeRepresentation> toCollection(List<Atividade> listaAtividades);

}
