package com.jjo.rankingatividades.api.controllers;

import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import com.jjo.rankingatividades.domain.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/atividades/{id}/finalizar")
@AllArgsConstructor
public class StatusController {

    private final AtividadeService atividadeService ;
    private final StatusService statusService;
    @PutMapping
    public ResponseEntity<Atividade> atividadeFinalizada (@PathVariable Long id) {
        Atividade atividade = atividadeService.findById(id);
        statusService.finalizarAtividade(atividade);
        return ResponseEntity.noContent().build() ;
    }

    @DeleteMapping
    public ResponseEntity<Atividade> atividadePendente (@PathVariable Long id) {
        Atividade atividade = atividadeService.findById(id);
        statusService.atividadePendente(atividade);
        return ResponseEntity.noContent().build() ;
    }


}
