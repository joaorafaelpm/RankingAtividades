package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.AlunoEAtividadeException;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class StatusService {

    private final AtividadeService atividadeService;

    public Atividade finalizarAtividade (Atividade atividade) {
        if (atividade.getStatus() == Status.FINALIZADA) {
            throw new AlunoEAtividadeException("Atividade já está finalizada!");
        }
        atividade.setStatus(Status.FINALIZADA);
        atividade.setDataFim(OffsetDateTime.now());
        return atividadeService.salvar(atividade) ;
    }

    public Atividade atividadePendente (Atividade atividade) {
        if (atividade.getStatus() == Status.PENDENTE) {
            throw new AlunoEAtividadeException("Atividade ainda está pendente!");
        }
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataFim(null);
        return atividadeService.salvar(atividade) ;
    }

}
