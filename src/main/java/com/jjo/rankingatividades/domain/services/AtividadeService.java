package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
@Transactional
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final AlunoService alunoService ;

    public Atividade procurarPeloId (Long id) {
        return atividadeRepository.findById(id).orElseThrow(()-> new NotFoundException("Atividade não encontrada!"));
    }

    public Atividade salvar (Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade addAtividade (Atividade atividade) {
        Aluno aluno = alunoService.procurarPorAtividade(atividade);
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataInicio(OffsetDateTime.now());
        atividade.setDataFim(null);
        aluno.adicionarAtividadeAluno(atividade);
        return atividadeRepository.save(atividade);
    }

    public Atividade atualizarAtividade (Long id ,String description) {
        Atividade atividadeAtualizada = procurarPeloId(id) ;
        atividadeAtualizada.setDescricao(description);
        return atividadeRepository.save(atividadeAtualizada);
    }

    public void deletar (Long id) {
        atividadeRepository.deleteById(id);
    }

}
