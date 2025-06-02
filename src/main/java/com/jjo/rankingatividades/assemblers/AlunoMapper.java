package com.jjo.rankingatividades.assemblers;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.models.AlunoRepresentation;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Bean
    Aluno alunoDTOToAluno (AlunoDTO alunoDTO);
    @Bean
    AlunoRepresentation alunoToAlunoRepresentation (Aluno aluno);

    @Bean
    List<AlunoRepresentation> toCollection(List<Aluno> listaAluno);

}
