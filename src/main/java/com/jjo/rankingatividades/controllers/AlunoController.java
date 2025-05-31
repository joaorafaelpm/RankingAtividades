package com.jjo.rankingatividades.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.jjo.rankingatividades.assemblers.AlunoAssembler;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.services.AlunoService;
import com.jjo.rankingatividades.domain.exceptions.AlunoException;
import com.jjo.rankingatividades.models.AlunoRepresentation;


@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService alunoService ;
    private final AlunoAssembler alunoAssembler;
    private final AlunoRepository alunoRepository;
    private final List<Aluno> listaAlunos = new ArrayList<>();


    @GetMapping
    public ResponseEntity<List<AlunoRepresentation>> pegarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoRepresentation> lista = alunoAssembler.toCollection(alunos);
        // POO, sem http:
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno.toString());
        }

        // AOS, resposta pelo json:
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoRepresentation> pegarAluno(@PathVariable Long id) {
        AlunoRepresentation aluno = alunoAssembler.toModel(alunoService.procurarPeloId(id));
        // POO, sem http:
        for (Aluno alunoLista : listaAlunos) {
            if (aluno.getId() == id) {
                System.out.println(aluno.toString());
            }
        }
        // AOS, resposta pelo json:
        return alunoRepository.findById(id)
                .map(alunoAssembler :: toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlunoRepresentation criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoAssembler.toEntity(alunoDTO);
        // POO, sem http:
        listaAlunos.add(aluno);
        // AOS, resposta pelo json:
        return alunoAssembler.toModel(alunoService.salvar(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoRepresentation> atualizarAluno
    (@PathVariable Long id , @Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoAssembler.toEntity(alunoDTO);
        // POO, sem http:
        aluno.setId(id);
        for (Aluno alunoAntigo : listaAlunos) {
            if (alunoAntigo.getId() == aluno.getId()) {
                int index = listaAlunos.indexOf(alunoAntigo);
                listaAlunos.remove(alunoAntigo);
                listaAlunos.add(index , aluno);
            }
        }

        // AOS, resposta pelo json:
        return ResponseEntity.ok().body(alunoAssembler.toModel(alunoService.salvar(aluno)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno (@PathVariable Long id) {
        // POO, sem http:
        for (Aluno alunoAntigo : listaAlunos) {
            if (alunoAntigo.getId() == id) {
                listaAlunos.remove(alunoAntigo);
            }
        }
        // AOS, resposta pelo json:
        alunoService.deletar(id);
        return ResponseEntity.status(204).build();
    }


}
