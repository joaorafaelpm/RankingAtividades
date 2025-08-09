package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
@Transactional
public class StatusService {

    private final AtividadeService atividadeService;

    public Atividade finalizarAtividade (Atividade atividade) {
        if (atividade.getStatus() == Status.FINALIZADA) {
            throw new EmailEmUsoException("Atividade já está finalizada!");
        }
        atividade.setStatus(Status.FINALIZADA);
        atividade.setDataFim(LocalDate.now());
        return atividadeService.save(atividade) ;
    }

    public Atividade atividadePendente (Atividade atividade) {
        if (atividade.getStatus() == Status.PENDENTE) {
            throw new EmailEmUsoException("Atividade ainda está pendente!");
        }
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataFim(null);
        return atividadeService.save(atividade) ;
    }

}
