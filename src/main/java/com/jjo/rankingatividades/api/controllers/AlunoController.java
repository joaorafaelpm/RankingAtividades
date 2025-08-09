package com.jjo.rankingatividades.api.controllers;

import com.jjo.rankingatividades.api.assemblers.AlunoMapper;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import com.jjo.rankingatividades.domain.DTOs.AlunoAtualizacaoDTO;
import com.jjo.rankingatividades.domain.exceptions.AnoNascimentoException;
import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.services.AlunoService;


@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService ;
    private final AlunoMapper alunoMapper ;


    @GetMapping
    public ResponseEntity<?> pegarAlunos() {
        var lista = alunoMapper.toCollection(alunoService.findAll());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoUniqueRepresentation> pegarAluno(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    alunoMapper.alunoToAlunoUniqueRepresentation(
                            alunoService.findById(id)
                    ));
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        try{
            Aluno aluno = alunoMapper.alunoDTOToAluno(alunoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.add(aluno)));
        }
        catch (AnoNascimentoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno
                    (@PathVariable Long id , @Valid @RequestBody AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        try {
            Aluno aluno = alunoMapper.alunoAtualizacaoDTOToAluno(alunoAtualizacaoDTO);
            var alunoAtualizado = alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.save(id, aluno));
            return ResponseEntity.ok().body(alunoAtualizado);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (EmailEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno (@PathVariable Long id) {
        try {
            alunoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
