package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.AtividadesException;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final AlunoService alunoService ;

    @Transactional
    public Atividade procurarPeloId (Long id) {
        return atividadeRepository.findById(id).orElseThrow(()-> new AtividadesException("Aluno não encontrado!"));
    }

    @Transactional
    public Atividade salvar (Atividade atividade) {
        Aluno aluno = alunoService.procurarPorAtividade(atividade);
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataInicio(OffsetDateTime.now());
        atividade.setDataFim(null);
        aluno.adicionarAtividade(atividade);
        return atividadeRepository.save(atividade);
    }


}
