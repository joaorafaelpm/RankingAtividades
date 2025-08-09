package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final AlunoService alunoService ;

    public List<Atividade> findAll () {
        return atividadeRepository.findAll();
    }

    public Atividade findById(Long id) {
        return atividadeRepository.findById(id).orElseThrow(()-> new NotFoundException("Atividade não encontrada!"));
    }

    public Atividade save(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade addAtividade (Atividade atividade) {
        Aluno aluno = alunoService.findByAtividade(atividade);
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataFim(null);
        aluno.adicionarAtividadeAluno(atividade);
        return atividadeRepository.save(atividade);
    }

    public Atividade atualizarAtividade (Long id ,String description) {
        Atividade atividadeAtualizada = findById(id) ;
        atividadeAtualizada.setDescricao(description);
        return atividadeRepository.save(atividadeAtualizada);
    }

    public void deleteById(Long id) {
        atividadeRepository.deleteById(id);
    }

}
