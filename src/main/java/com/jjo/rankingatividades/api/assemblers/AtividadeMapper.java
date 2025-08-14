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

/**
 * Mapper responsável por converter entre entidades de domínio (Atividade),
 * DTOs (AtividadeDTO) e representações para API (AtividadePagableRepresentation, AtividadeUniqueRepresentation).
 * Utiliza o MapStruct para gerar automaticamente o código de mapeamento.
 */
@Mapper(componentModel = "spring")
public interface AtividadeMapper {

    // Dentro dessa classe eu uso o @Bean, assim como em infrastructure/config/ModelMapperConfig.java eu determino que a classe ModelMapper faz parte do Spring com @Bean, aqui eu determino que cada função também faz parte do Spring

    /**
     * Converte um AtividadeDTO para a entidade Atividade.
     * O campo alunoId no DTO é convertido em um objeto Aluno via método auxiliar {@link #toAluno(Long)}.
     */
    @Mapping(source = "alunoId", target = "aluno")
    Atividade atividadeDTOToAtividade(AtividadeDTO atividadeDTO);

    /**
     * Converte uma entidade Atividade para a representação detalhada.
     */
    AtividadeUniqueRepresentation atividadeToAtividadeUniqueRepresentation(Atividade atividade);

    /**
     * Converte uma lista de Atividade para uma lista de representações pagináveis.
     */
    List<AtividadePagableRepresentation> toCollection(List<Atividade> listaAtividades);

    /**
     * Método auxiliar usado pelo MapStruct para converter um ID (alunoId) em um objeto Aluno.
     * Apenas define o ID, sem buscar no banco.
     * Caso precise dos dados completos do aluno, é necessário integrar com um serviço.
     */
    default Aluno toAluno(Long alunoId) {
        if (alunoId == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        return aluno;
    }
}
