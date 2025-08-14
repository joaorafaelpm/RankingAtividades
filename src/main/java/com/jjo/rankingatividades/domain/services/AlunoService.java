package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.AnoNascimentoException;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Atividade;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Slf4j // Cria um logger para facilitar o log de informações
@Service // Indica que esta classe é um serviço do Spring
@AllArgsConstructor // Cria automaticamente um construtor com todas as dependencias da classe
@Transactional // Garante que os métodos sejam executados dentro de uma transação
public class AlunoService {

    private final AlunoRepository alunoRepository; // Repositório para acesso ao banco de dados dos alunos

    // Retorna os alunos paginados conforme o Pageable passado como argumento
    public Page<Aluno> getAlunosPaginated(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    // Busca um aluno pelo ID; lança exceção se não encontrado
    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado!"));
    }

    // Busca um aluno com base na atividade (através do ID do aluno associado à atividade)
    public Aluno findByAtividade(Atividade atividade) {
        return alunoRepository.findById(atividade.getAluno().getId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));
    }

    // Adiciona um novo aluno ao banco de dados
    public Aluno add(Aluno aluno) {
        verifyAge(aluno.getDataNascimento()); // Verifica se a idade é válida
        if (emailExiste(aluno)) { // Verifica se o email já está em uso
            throw new EmailEmUsoException("Email já está em uso!");
        }
        return alunoRepository.saveAndFlush(aluno); // Salva o aluno no banco
    }

    // Atualiza os dados de um aluno existente
    public Aluno save(Long id, Aluno aluno) {
        Aluno alunoExistente = findById(id); // Busca o aluno existente pelo ID

        // Atualiza o nome se for diferente do atual
        if (aluno.getName() != null && !aluno.getName().equals(alunoExistente.getName())) {
            alunoExistente.setName(aluno.getName());
        }

        // Atualiza a data de nascimento se for diferente da atual
        if (aluno.getDataNascimento() != null
                && !aluno.getDataNascimento().equals(alunoExistente.getDataNascimento())) {
            alunoExistente.setDataNascimento(aluno.getDataNascimento());
        }

        // Atualiza o email se for diferente e se não estiver em uso por outro aluno
        if (aluno.getEmail() != null && !aluno.getEmail().equals(alunoExistente.getEmail())) {
            // Se o email estiver sendo usado
            if (emailExiste(aluno)) {
                // Pego o aluno referente ao email
                Aluno alunoDoEmail = alunoRepository.findByEmail(aluno.getEmail()).orElseThrow();
                // Se o id dele não for o mesmo do usuário atual lança exceção
                if (!alunoExistente.getId().equals(alunoDoEmail.getId())) {
                    throw new EmailEmUsoException("Email já está em uso!");
                }
            }
            // Se não estiver sendo usado atribui ao usuário
            alunoExistente.setEmail(aluno.getEmail());
        }

        alunoExistente.setId(id); // Garante que o ID não seja alterado
        return alunoRepository.save(alunoExistente); // Salva as alterações
    }

    // Verifica se o email já está cadastrado no banco
    public boolean emailExiste(Aluno aluno) {
        return alunoRepository.findByEmail(aluno.getEmail()).isPresent();
    }

    // Verifica se o aluno tem no mínimo 13 anos
    public void verifyAge(LocalDate dataNascimento) {
        //  Pega o ano atual e subtrai do ano de nascimento do usuário
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 13) {
            throw new AnoNascimentoException("O aluno deve ter pelo menos 13 anos!");
        }
    }

    // Deleta um aluno pelo ID. Retorna status 204 se sucesso, ou lança exceção se não encontrado
    public ResponseEntity<?> deleteById(Long alunoId) {
        if (alunoRepository.findById(alunoId).isPresent()) {
            alunoRepository.deleteById(alunoId);
            return ResponseEntity.noContent().build(); // HTTP 204 - No Content
        }
        throw new NotFoundException("Aluno não encontrado!");
    }

}
