package com.jjo.rankingatividades.api.controllers;

import com.jjo.rankingatividades.api.assemblers.AlunoMapper;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AlunoAtualizacaoDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.services.AlunoService;

@RestController
@RequestMapping("/alunos") // Define a porta padrão para acessar as informações
@AllArgsConstructor // Gera o construtor com todos os atributos
@Slf4j // Permite registrar logs no controller
public class AlunoController {

    private final AlunoService alunoService; // Serviço responsável pela lógica de negócio de Aluno
    private final AlunoMapper alunoMapper;   // Mapper responsável por converter DTOs, Models e Representations

    // GET: lista de alunos com paginação
    @GetMapping
    public PagedModel<?> pegarAlunos(@PageableDefault Pageable pageable) {
        // Chama o serviço para pegar alunos paginados e converte para representação
        return new PagedModel<>(alunoMapper.toModel(alunoService.getAlunosPaginated(pageable)));
    }

    // GET: busca um aluno específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AlunoUniqueRepresentation> pegarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(
                alunoMapper.alunoToAlunoUniqueRepresentation(
                        alunoService.findById(id)) // Busca aluno pelo serviço
        );
    }

    // POST: cria um novo aluno
    @ResponseStatus(HttpStatus.CREATED) // Define status HTTP 201 (Criado)
    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        // Converte DTO em entidade e salva usando o serviço
        Aluno aluno = alunoMapper.alunoDTOToAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.add(aluno)));
    }

    // PUT: atualiza um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id,
            @Valid @RequestBody AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        // Converte DTO de atualização em entidade
        Aluno aluno = alunoMapper.alunoAtualizacaoDTOToAluno(alunoAtualizacaoDTO);
        var alunoAtualizado = alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.save(id, aluno));
        return ResponseEntity.ok().body(alunoAtualizado); // Retorna o aluno atualizado
    }

    // DELETE: remove um aluno pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable Long id) {
        alunoService.deleteById(id); // Deleta usando o serviço
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
