package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Atividade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.exceptions.AlunoEAtividadeException;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AlunoService {

    private final AlunoRepository alunoRepository;


    public Aluno procurarPeloId (Long id) {
        return alunoRepository.findById(id).orElseThrow(()-> new NotFoundException("Aluno não encontrado!"));
    }

    public Aluno procurarPorAtividade (Atividade atividade) {
        return alunoRepository.findById(atividade.getAluno().getId())
                .orElseThrow(()-> new NotFoundException("Aluno não encontrado!"));
    }

    public Aluno salvar(Aluno aluno) {
        if (emailEmUso(aluno)) {
            throw new AlunoEAtividadeException("Email já está em uso!");
        }

        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id , Aluno aluno ) {
        List<Atividade> atividades = procurarPeloId(id).getAtividades();
        aluno.setAtividades(atividades);

        if (emailEmUso(aluno)) {
            Aluno alunoDoEmail = alunoRepository.findByEmail(aluno.getEmail()).orElseThrow() ;
            if (aluno.getId() == alunoDoEmail.getId()) {
                return alunoRepository.save(aluno);
            }
            throw new AlunoEAtividadeException("Email já está em uso!");
        }
        return alunoRepository.save(aluno);
    }

    public boolean emailEmUso (Aluno aluno) {
        return alunoRepository.findByEmail(aluno.getEmail()).isPresent();
    }

    public void deletar (Long alunoId) {
        alunoRepository.deleteById(alunoId);
    }



}
