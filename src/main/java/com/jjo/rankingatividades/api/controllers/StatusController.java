package com.jjo.rankingatividades.api.controllers;

import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import com.jjo.rankingatividades.domain.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades/{id}/finalizar") // Endpoint específico para finalizar ou reabrir atividades
@AllArgsConstructor
public class StatusController {

    private final AtividadeService atividadeService; // Serviço para buscar atividades
    private final StatusService statusService;       // Serviço para alterar status da atividade

    // PUT: marca a atividade como finalizada
    @PutMapping
    public ResponseEntity<Atividade> atividadeFinalizada(@PathVariable Long id) {
        Atividade atividade = atividadeService.findById(id); // Busca a atividade pelo ID
        statusService.finalizarAtividade(atividade);        // Chama serviço para finalizar
        return ResponseEntity.noContent().build();           // Retorna 204 No Content
    }

    // DELETE: reabre a atividade, colocando como pendente
    @DeleteMapping
    public ResponseEntity<Atividade> atividadePendente(@PathVariable Long id) {
        Atividade atividade = atividadeService.findById(id);
        statusService.atividadePendente(atividade);        // Altera status para pendente
        return ResponseEntity.noContent().build();
    }
}
