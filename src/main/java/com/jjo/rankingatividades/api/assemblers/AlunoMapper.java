package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.api.models.AlunoPagableRepresentation;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AlunoAtualizacaoDTO;
import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Bean
    Aluno alunoDTOToAluno (AlunoDTO alunoDTO);

    @Bean
    Aluno alunoAtualizacaoDTOToAluno(AlunoAtualizacaoDTO alunoAtualizacaoDTO);

    @Bean
    AlunoUniqueRepresentation alunoToAlunoUniqueRepresentation(Aluno aluno);


    @Bean
    List<AlunoPagableRepresentation> toCollection(List<Aluno> listaAluno);

    @Bean
    AlunoPagableRepresentation toRepresentation(Aluno aluno);

    default Page<AlunoPagableRepresentation> toModel(Page<Aluno> listaAluno) {
        return listaAluno.map(this::toRepresentation);
    }





}
