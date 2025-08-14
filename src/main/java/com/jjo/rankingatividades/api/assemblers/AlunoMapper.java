package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.api.models.AlunoPagableRepresentation;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AlunoAtualizacaoDTO;
import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper responsável por converter entre entidades de domínio (Aluno),
 * DTOs (AlunoDTO, AlunoAtualizacaoDTO) e representações para API (AlunoPagableRepresentation, AlunoUniqueRepresentation).
 * Utiliza o MapStruct para gerar automaticamente o código de mapeamento.
 */
@Mapper(componentModel = "spring")
public interface AlunoMapper {


    // Dentro dessa classe eu uso o @Bean, assim como em infrastructure/config/ModelMapperConfig.java eu determino que a classe ModelMapper faz parte do Spring com @Bean, aqui eu determino que cada função também faz parte do Spring

    /**
     * Converte um AlunoDTO para a entidade Aluno.
     * Usado normalmente no cadastro de um novo aluno.
     */
    Aluno alunoDTOToAluno(AlunoDTO alunoDTO);

    /**
     * Converte um DTO de atualização (dados parciais) para a entidade Aluno.
     * Usado em requisições PUT/PATCH.
     */
    Aluno alunoAtualizacaoDTOToAluno(AlunoAtualizacaoDTO alunoAtualizacaoDTO);

    /**
     * Converte um Aluno para uma representação única/detalhada (exibição completa no endpoint).
     */
    AlunoUniqueRepresentation alunoToAlunoUniqueRepresentation(Aluno aluno);

    /**
     * Converte uma lista de Aluno para uma lista de representações pagináveis.
     */
    List<AlunoPagableRepresentation> toCollection(List<Aluno> listaAluno);

    /**
     * Converte um único Aluno para a sua representação paginável.
     */
    AlunoPagableRepresentation toRepresentation(Aluno aluno);

    /**
     * Converte uma página de Aluno para uma página de representações pagináveis.
     * Esse método é manual (default) porque o MapStruct não lida diretamente com Page<T>.
     */
    default Page<AlunoPagableRepresentation> toModel(Page<Aluno> listaAluno) {
        return listaAluno.map(this::toRepresentation);
    }
}
