package com.jjo.rankingatividades.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.models.Status;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jjo.rankingatividades.assemblers.AtividadeAssembler;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import com.jjo.rankingatividades.models.AtividadeRepresentation;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/atividades")
@AllArgsConstructor
public class AtividadeController {

    private final AtividadeAssembler atividadeAssembler ;
    private final AtividadeRepository atividadeRepository ;
    private final AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeRepresentation>> pegarAtividades () {
        List<AtividadeRepresentation> atividades = atividadeAssembler.toCollection(atividadeRepository.findAll());
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeRepresentation> pegarAtividades (@PathVariable Long id) {
        AtividadeRepresentation atividade = atividadeAssembler.toModel(atividadeService.procurarPeloId(id));
        return ResponseEntity.ok(atividade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeRepresentation  criarAtividade (@Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeAssembler.toEntity(atividadeDTO);
        return atividadeAssembler.toModel(atividadeService.salvar(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeRepresentation> atualizarAtividade (@PathVariable Long id , @Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeAssembler.toEntity(atividadeDTO);
        return ResponseEntity.ok(atividadeAssembler.toModel(atividadeService.salvar(atividade))) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Atividade> deletarAtividade (@PathVariable Long id) {
        atividadeRepository.deleteById(id) ;
        return ResponseEntity.status(204).build();
    }





}
