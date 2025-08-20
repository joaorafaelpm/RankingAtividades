package com.jjo.rankingatividades.api.controllers;

import java.util.List;
import com.jjo.rankingatividades.api.assemblers.AtividadeMapper;
import com.jjo.rankingatividades.api.models.AtividadePagableRepresentation;
import com.jjo.rankingatividades.api.models.AtividadeUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AtividadeDTO;
import com.jjo.rankingatividades.domain.DTOs.DescriptionDTO;
import com.jjo.rankingatividades.domain.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jjo.rankingatividades.domain.models.Atividade;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/atividades") // Rota base para todas as operações de atividade
@AllArgsConstructor
public class AtividadeController {

    private final AtividadeMapper atividadeMapper; // Mapper entre DTOs, Models e Representations
    private final AtividadeService atividadeService; // Serviço com a lógica de negócio de atividades

    // GET: lista todas as atividades
    @GetMapping
    public PagedModel<?> pegarAlunos(@PageableDefault Pageable pageable) {
        // Chama o serviço para pegar alunos paginados e converte para representação
        return new PagedModel<>(atividadeMapper.toPageable(atividadeService.getAtividadesPaginated(pageable)));
    }

    // GET: busca uma atividade pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AtividadeUniqueRepresentation> pegarAtividades(@PathVariable Long id) {
        AtividadeUniqueRepresentation atividade = atividadeMapper.atividadeToAtividadeUniqueRepresentation(
                atividadeService.findById(id));
        return ResponseEntity.ok(atividade);
    }

    // POST: cria uma nova atividade
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeUniqueRepresentation criarAtividade(@Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeMapper.atividadeDTOToAtividade(atividadeDTO);
        return atividadeMapper.atividadeToAtividadeUniqueRepresentation(atividadeService.addAtividade(atividade));
    }

    // PUT: atualiza apenas a descrição da atividade
    @PutMapping("/{id}")
    public ResponseEntity<AtividadeUniqueRepresentation> atualizarAtividade(@PathVariable Long id,
            @Valid @RequestBody DescriptionDTO descriptionDTO) {
        return ResponseEntity.ok(
                atividadeMapper.atividadeToAtividadeUniqueRepresentation(
                        atividadeService.atualizarAtividade(id, descriptionDTO.getDescricao()))
        );
    }

    // DELETE: remove atividade pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Atividade> deletarAtividade(@PathVariable Long id) {
        atividadeService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
        
    }
}
