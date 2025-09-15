package com.jjo.rankingatividades.api.assemblers;

import com.jjo.rankingatividades.api.models.AlunoPagableRepresentation;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import com.jjo.rankingatividades.api.models.AtividadeStandartModel;
import com.jjo.rankingatividades.domain.DTOs.AlunoAtualizacaoDTO;
import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-14T12:22:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class AlunoMapperImpl implements AlunoMapper {

    @Override
    public Aluno alunoDTOToAluno(AlunoDTO alunoDTO) {
        if ( alunoDTO == null ) {
            return null;
        }

        Aluno aluno = new Aluno();

        aluno.setName( alunoDTO.getName() );
        aluno.setEmail( alunoDTO.getEmail() );
        aluno.setDataNascimento( alunoDTO.getDataNascimento() );
        aluno.setCurso( alunoDTO.getCurso() );
        aluno.setClasse( alunoDTO.getClasse() );

        return aluno;
    }

    @Override
    public Aluno alunoAtualizacaoDTOToAluno(AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        if ( alunoAtualizacaoDTO == null ) {
            return null;
        }

        Aluno aluno = new Aluno();

        aluno.setEmail( alunoAtualizacaoDTO.getEmail() );
        aluno.setCurso( alunoAtualizacaoDTO.getCurso() );
        aluno.setClasse( alunoAtualizacaoDTO.getClasse() );

        return aluno;
    }

    @Override
    public AlunoUniqueRepresentation alunoToAlunoUniqueRepresentation(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoUniqueRepresentation alunoUniqueRepresentation = new AlunoUniqueRepresentation();

        alunoUniqueRepresentation.setId( aluno.getId() );
        alunoUniqueRepresentation.setName( aluno.getName() );
        alunoUniqueRepresentation.setEmail( aluno.getEmail() );
        alunoUniqueRepresentation.setDataNascimento( aluno.getDataNascimento() );
        alunoUniqueRepresentation.setCurso( aluno.getCurso() );
        alunoUniqueRepresentation.setClasse( aluno.getClasse() );
        alunoUniqueRepresentation.setAtividades( atividadeListToAtividadeStandartModelList( aluno.getAtividades() ) );

        return alunoUniqueRepresentation;
    }

    @Override
    public List<AlunoPagableRepresentation> toCollection(List<Aluno> listaAluno) {
        if ( listaAluno == null ) {
            return null;
        }

        List<AlunoPagableRepresentation> list = new ArrayList<AlunoPagableRepresentation>( listaAluno.size() );
        for ( Aluno aluno : listaAluno ) {
            list.add( toRepresentation( aluno ) );
        }

        return list;
    }

    @Override
    public AlunoPagableRepresentation toRepresentation(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoPagableRepresentation alunoPagableRepresentation = new AlunoPagableRepresentation();

        alunoPagableRepresentation.setId( aluno.getId() );
        alunoPagableRepresentation.setName( aluno.getName() );
        alunoPagableRepresentation.setEmail( aluno.getEmail() );
        alunoPagableRepresentation.setDataNascimento( aluno.getDataNascimento() );
        alunoPagableRepresentation.setCurso( aluno.getCurso() );
        alunoPagableRepresentation.setClasse( aluno.getClasse() );

        return alunoPagableRepresentation;
    }

    protected AtividadeStandartModel atividadeToAtividadeStandartModel(Atividade atividade) {
        if ( atividade == null ) {
            return null;
        }

        AtividadeStandartModel atividadeStandartModel = new AtividadeStandartModel();

        atividadeStandartModel.setId( atividade.getId() );
        atividadeStandartModel.setDescricao( atividade.getDescricao() );
        atividadeStandartModel.setStatus( atividade.getStatus() );
        atividadeStandartModel.setDataInicio( atividade.getDataInicio() );
        atividadeStandartModel.setDataFim( atividade.getDataFim() );

        return atividadeStandartModel;
    }

    protected List<AtividadeStandartModel> atividadeListToAtividadeStandartModelList(List<Atividade> list) {
        if ( list == null ) {
            return null;
        }

        List<AtividadeStandartModel> list1 = new ArrayList<AtividadeStandartModel>( list.size() );
        for ( Atividade atividade : list ) {
            list1.add( atividadeToAtividadeStandartModel( atividade ) );
        }

        return list1;
    }
}
