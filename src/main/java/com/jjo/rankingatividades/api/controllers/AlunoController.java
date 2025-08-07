package com.jjo.rankingatividades.api.controllers;

import java.util.ArrayList;
import java.util.List;

import com.jjo.rankingatividades.api.assemblers.AlunoMapper;
import com.jjo.rankingatividades.api.models.AlunoPagableRepresentation;
import com.jjo.rankingatividades.api.models.AlunoUniqueRepresentation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.services.AlunoService;


@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService alunoService ;
    private final AlunoMapper alunoMapper ;
    private final AlunoRepository alunoRepository;


    @GetMapping
    public ResponseEntity<List<AlunoPagableRepresentation>> pegarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoPagableRepresentation> lista = alunoMapper.toCollection(alunos);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoUniqueRepresentation> pegarAluno(@PathVariable Long id) {
        AlunoUniqueRepresentation aluno = alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.procurarPeloId(id));
        return alunoRepository.findById(id)
                .map(alunoMapper ::alunoToAlunoUniqueRepresentation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlunoUniqueRepresentation criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.alunoDTOToAluno(alunoDTO);
        return alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.salvar(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoUniqueRepresentation> atualizarAluno
    (@PathVariable Long id , @Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.alunoDTOToAluno(alunoDTO);
        return ResponseEntity.ok().body(alunoMapper.alunoToAlunoUniqueRepresentation(alunoService.atualizar(id ,aluno)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno (@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.status(204).build();
    }


}
