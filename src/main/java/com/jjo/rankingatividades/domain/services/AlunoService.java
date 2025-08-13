package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.AnoNascimentoException;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Atividade;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.web.*;
import org.springframework.http.ResponseEntity;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Page<Aluno> getAlunosPaginated(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }


    public List<?> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado!"));
    }

    public Aluno findByAtividade(Atividade atividade) {
        return alunoRepository.findById(atividade.getAluno().getId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));
    }

    public Aluno add(Aluno aluno) {
        try {
            verifyAge(aluno.getDataNascimento());
            if (emailExiste(aluno)) {
                throw new EmailEmUsoException("Email já está em uso!");
            }
            return alunoRepository.saveAndFlush(aluno);
        } catch (AnoNascimentoException e) {
            throw new AnoNascimentoException(e.getMessage());
        }

    }

    public Aluno save(Long id, Aluno aluno) {
        Aluno alunoExistente = findById(id);

        if (aluno.getName() != null && !aluno.getName().equals(alunoExistente.getName())) {
            alunoExistente.setName(aluno.getName());
        }
        if (aluno.getDataNascimento() != null
                && !aluno.getDataNascimento().equals(alunoExistente.getDataNascimento())) {
            alunoExistente.setDataNascimento(aluno.getDataNascimento());
        }
        if (aluno.getEmail() != null && !aluno.getEmail().equals(alunoExistente.getEmail())) {
            if (emailExiste(aluno)) {
                Aluno alunoDoEmail = alunoRepository.findByEmail(aluno.getEmail()).orElseThrow();
                if (!alunoExistente.getId().equals(alunoDoEmail.getId())) {
                    throw new EmailEmUsoException("Email já está em uso!");
                }
            }
            alunoExistente.setEmail(aluno.getEmail());
        }

        alunoExistente.setId(id);
        return alunoRepository.save(alunoExistente);

    }

    public boolean emailExiste(Aluno aluno) {
        return alunoRepository.findByEmail(aluno.getEmail()).isPresent();
    }

    public void verifyAge(LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 13) {
            throw new AnoNascimentoException("O aluno deve ter pelo menos 13 anos!");
        }
    }

    public ResponseEntity<?> deleteById(Long alunoId) {
        if (alunoRepository.findById(alunoId).isPresent()) {
            alunoRepository.deleteById(alunoId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Aluno não encontrado!");
    }

}
