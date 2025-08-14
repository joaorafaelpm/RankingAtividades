package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.AtividadeException;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Serviço responsável por manipular o status das atividades.
 * Contém regras para finalizar ou reabrir (deixar pendente) atividades.
 */
@Service // Indica que esta classe é um componente de serviço do Spring
@AllArgsConstructor // Gera automaticamente um construtor com todos os atributos
@Transactional // Garante que as operações de banco nesta classe sejam executadas em uma transação
public class StatusService {

    // Serviço de atividades, usado para buscar e salvar alterações
    private final AtividadeService atividadeService;

    /**
     * Finaliza uma atividade.
     * - Verifica se já está finalizada (caso esteja, lança exceção).
     * - Atualiza o status para FINALIZADA.
     * - Define a data de finalização como a data atual.
     * @param atividade Atividade a ser finalizada.
     * @return Atividade com status finalizado.
     */
    public Atividade finalizarAtividade(Atividade atividade) {
        if (atividade.getStatus() == Status.FINALIZADA) {
            throw new AtividadeException("Atividade já está finalizada!");
        }
        atividade.setStatus(Status.FINALIZADA);
        atividade.setDataFim(LocalDate.now());
        return atividadeService.save(atividade);
    }

    /**
     * Reabre uma atividade, deixando-a como pendente.
     * - Verifica se já está pendente (caso esteja, lança exceção).
     * - Atualiza o status para PENDENTE.
     * - Remove a data de finalização (coloca null).
     * @param atividade Atividade a ser reaberta.
     * @return Atividade com status pendente.
     */
    public Atividade atividadePendente(Atividade atividade) {
        if (atividade.getStatus() == Status.PENDENTE) {
            throw new AtividadeException("Atividade ainda está pendente!");
        }
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataFim(null);
        return atividadeService.save(atividade);
    }
}
