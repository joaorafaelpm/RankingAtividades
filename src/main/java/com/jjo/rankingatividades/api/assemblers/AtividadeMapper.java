package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.api.models.AtividadeUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.api.models.AtividadePagableRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtividadeMapper {

    @Bean
    @Mapping(source = "alunoId", target = "aluno")
    Atividade atividadeDTOToAtividade (AtividadeDTO atividadeDTO);

    @Bean
    AtividadeUniqueRepresentation atividadeToAtividadeUniqueRepresentation(Atividade atividade);

    @Bean
    List<AtividadePagableRepresentation> toCollection(List<Atividade> listaAtividades);

//    O mapStruct faz a conversão desse alunoId em um objeto da classe aluno
    default Aluno toAluno(Long alunoId) {
        if (alunoId == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        return aluno; // Aqui você pode buscar o aluno em um serviço, se necessário
    }
}
