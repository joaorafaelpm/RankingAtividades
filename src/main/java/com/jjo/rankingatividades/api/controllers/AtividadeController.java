package com.jjo.rankingatividades.api.controllers;

import java.util.List;

import com.jjo.rankingatividades.api.assemblers.AtividadeMapper;
import com.jjo.rankingatividades.api.models.AtividadePagableRepresentation;
import com.jjo.rankingatividades.api.models.AtividadeUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.DTOs.DescriptionDTO;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jjo.rankingatividades.domain.models.Atividade;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/atividades")
@AllArgsConstructor
public class AtividadeController {

    private final AtividadeMapper atividadeMapper;
    private final AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<?> pegarAtividades () {
        List<AtividadePagableRepresentation> atividades = atividadeMapper.toCollection(atividadeService.findAll());
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeUniqueRepresentation> pegarAtividades (@PathVariable Long id) {
        AtividadeUniqueRepresentation atividade = atividadeMapper.atividadeToAtividadeUniqueRepresentation(atividadeService.findById(id));
        return ResponseEntity.ok(atividade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeUniqueRepresentation criarAtividade (@Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeMapper.atividadeDTOToAtividade(atividadeDTO);
        return atividadeMapper.atividadeToAtividadeUniqueRepresentation(atividadeService.addAtividade(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeUniqueRepresentation> atualizarAtividade (@PathVariable Long id , @Valid @RequestBody DescriptionDTO descriptionDTO) {
        return ResponseEntity.ok(atividadeMapper.atividadeToAtividadeUniqueRepresentation(atividadeService.atualizarAtividade(id , descriptionDTO.getDescricao()))) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Atividade> deletarAtividade (@PathVariable Long id) {
        try {
            atividadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
