package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.models.AlunoRepresentation;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jjo.rankingatividades.assemblers.AlunoAssembler;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.exceptions.AlunoException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoAssembler alunoAssembler;

    @Transactional
    public Aluno procurarPeloId (Long id) {
        return alunoRepository.findById(id).orElseThrow(()-> new AlunoException("Aluno não encontrado!"));
    }

    @Transactional
    public Aluno procurarPorAtividade (Atividade atividade) {
        return alunoRepository.findById(atividade.getAluno().getId())
                .orElseThrow(()-> new AlunoException("Aluno não encontrado!"));
    }



    @Transactional
    public Aluno salvar(Aluno aluno) {
        boolean emailEmUso = alunoRepository.findByEmail(aluno.getEmail())
                .filter(alunoAtual -> !alunoAtual.equals(aluno))
                .isPresent();
        if (emailEmUso) {
            throw new AlunoException("Email já está em uso!");
        }
        return alunoRepository.save(aluno);
    }



    @Transactional
    public void deletar (Long alunoId) {
        alunoRepository.deleteById(alunoId);
    }



}
