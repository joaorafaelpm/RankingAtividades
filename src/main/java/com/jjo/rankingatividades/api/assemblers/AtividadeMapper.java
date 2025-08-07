package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.api.models.AtividadeRepresentation;
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
