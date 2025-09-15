package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.api.models.AlunoStandartModel;
import com.jjo.rankingatividades.api.models.AtividadePagableRepresentation;
import com.jjo.rankingatividades.api.models.AtividadeUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AlunoId;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-14T12:22:45-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class AtividadeMapperImpl implements AtividadeMapper {

    @Override
    public Atividade atividadeDTOToAtividade(AtividadeDTO atividadeDTO) {
        if ( atividadeDTO == null ) {
            return null;
        }

        Atividade atividade = new Atividade();

        atividade.setAluno( alunoIdToAluno( atividadeDTO.getAlunoId() ) );
        atividade.setDescricao( atividadeDTO.getDescricao() );

        return atividade;
    }

    @Override
    public AtividadeUniqueRepresentation atividadeToAtividadeUniqueRepresentation(Atividade atividade) {
        if ( atividade == null ) {
            return null;
        }

        AtividadeUniqueRepresentation atividadeUniqueRepresentation = new AtividadeUniqueRepresentation();

        atividadeUniqueRepresentation.setId( atividade.getId() );
        atividadeUniqueRepresentation.setAluno( alunoToAlunoStandartModel( atividade.getAluno() ) );
        atividadeUniqueRepresentation.setDescricao( atividade.getDescricao() );
        atividadeUniqueRepresentation.setStatus( atividade.getStatus() );
        atividadeUniqueRepresentation.setDataInicio( atividade.getDataInicio() );
        atividadeUniqueRepresentation.setDataFim( atividade.getDataFim() );

        return atividadeUniqueRepresentation;
    }

    @Override
    public List<AtividadePagableRepresentation> toCollection(List<Atividade> listaAtividades) {
        if ( listaAtividades == null ) {
            return null;
        }

        List<AtividadePagableRepresentation> list = new ArrayList<AtividadePagableRepresentation>( listaAtividades.size() );
        for ( Atividade atividade : listaAtividades ) {
            list.add( toRepresentation( atividade ) );
        }

        return list;
    }

    @Override
    public AtividadePagableRepresentation toRepresentation(Atividade atividade) {
        if ( atividade == null ) {
            return null;
        }

        AtividadePagableRepresentation atividadePagableRepresentation = new AtividadePagableRepresentation();

        atividadePagableRepresentation.setId( atividade.getId() );
        atividadePagableRepresentation.setDescricao( atividade.getDescricao() );
        atividadePagableRepresentation.setStatus( atividade.getStatus() );
        atividadePagableRepresentation.setDataInicio( atividade.getDataInicio() );
        atividadePagableRepresentation.setDataFim( atividade.getDataFim() );

        return atividadePagableRepresentation;
    }

    protected Aluno alunoIdToAluno(AlunoId alunoId) {
        if ( alunoId == null ) {
            return null;
        }

        Aluno aluno = new Aluno();

        aluno.setId( alunoId.getId() );

        return aluno;
    }

    protected AlunoStandartModel alunoToAlunoStandartModel(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoStandartModel alunoStandartModel = new AlunoStandartModel();

        alunoStandartModel.setId( aluno.getId() );
        alunoStandartModel.setName( aluno.getName() );

        return alunoStandartModel;
    }
}
