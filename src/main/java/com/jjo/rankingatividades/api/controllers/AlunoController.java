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
@RequestMapping("/alunos")
@AllArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoMapper alunoMapper;

    // @GetMapping
    // public ResponseEntity<?> pegarAlunos() {
    // var lista = alunoMapper.toCollection(alunoService.findAll());
    // return ResponseEntity.ok(lista);
    // }

    // TODO: Adaptar para o pageableModel
    @GetMapping
    public PagedModel<?> pegarAlunos(@PageableDefault Pageable pageable) {
        return new PagedModel<>(alunoMapper.toModel(alunoService.getAlunosPaginated(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoUniqueRepresentation> pegarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(
                alunoMapper.alunoToAlunoUniqueRepresentation(
                        alunoService.findById(id)));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.alunoDTOToAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.add(aluno)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id,
            @Valid @RequestBody AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        Aluno aluno = alunoMapper.alunoAtualizacaoDTOToAluno(alunoAtualizacaoDTO);
        var alunoAtualizado = alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.save(id, aluno));
        return ResponseEntity.ok().body(alunoAtualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
