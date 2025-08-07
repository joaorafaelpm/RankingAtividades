package com.jjo.rankingatividades.api.controllers;

import java.util.List;

import com.jjo.rankingatividades.api.assemblers.AtividadeAssembler;
import com.jjo.rankingatividades.api.assemblers.AtividadeMapper;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.DTOs.DescriptionDTO;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import com.jjo.rankingatividades.api.models.AtividadeRepresentation;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/atividades")
@AllArgsConstructor
public class AtividadeController {

    private final AtividadeAssembler atividadeAssembler;
    private final AtividadeMapper atividadeMapper;
    private final AtividadeRepository atividadeRepository ;
    private final AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeRepresentation>> pegarAtividades () {
        List<AtividadeRepresentation> atividades = atividadeMapper.toCollection(atividadeRepository.findAll());
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeRepresentation> pegarAtividades (@PathVariable Long id) {
        AtividadeRepresentation atividade = atividadeMapper.atividadeToAtividadeRepresentation(atividadeService.procurarPeloId(id));
        return ResponseEntity.ok(atividade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeRepresentation  criarAtividade (@Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeAssembler.toEntity(atividadeDTO);
        return atividadeMapper.atividadeToAtividadeRepresentation(atividadeService.addAtividade(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeRepresentation> atualizarAtividade (@PathVariable Long id , @Valid @RequestBody DescriptionDTO descriptionDTO) {
        return ResponseEntity.ok(atividadeMapper.atividadeToAtividadeRepresentation(atividadeService.atualizarAtividade(id , descriptionDTO.getDescricao()))) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Atividade> deletarAtividade (@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.status(204).build();
    }




}
