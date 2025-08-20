package com.jjo.rankingatividades.domain.services;

import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.models.Atividade;
import com.jjo.rankingatividades.domain.models.Status;
import com.jjo.rankingatividades.domain.repositories.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço responsável por gerenciar operações relacionadas à entidade Atividade.
 * Contém a lógica de negócios e a orquestração entre repositórios e outros serviços.
 */
@Service // Indica que esta classe é um componente de serviço do Spring
@AllArgsConstructor // Gera um construtor com todos os atributos (injeção via construtor)
@Transactional // Garante que todas as operações de banco nesta classe sejam executadas em uma transação
public class AtividadeService {

    // Repositório para acesso ao banco de dados da entidade Atividade
    private final AtividadeRepository atividadeRepository;

    // Serviço para manipulação de dados de alunos
    private final AlunoService alunoService;

    /**
     * Retorna todas as atividades cadastradas no banco.
     * @return Lista de atividades.
     */
    public Page<Atividade> getAtividadesPaginated(Pageable pageable) {
        return atividadeRepository.findAll(pageable);
    }


    /**
     * Busca uma atividade pelo seu ID.
     * Caso não seja encontrada, lança uma exceção personalizada.
     * @param id ID da atividade.
     * @return Atividade encontrada.
     */
    public Atividade findById(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Atividade não encontrada!"));
    }

    /**
     * Salva uma nova atividade ou atualiza uma existente no banco de dados.
     * @param atividade Objeto a ser salvo.
     * @return Atividade salva.
     */
    public Atividade save(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    /**
     * Adiciona uma nova atividade a um aluno.
     * - Busca o aluno associado à atividade.
     * - Define o status inicial como PENDENTE.
     * - Define a data de fim como nula (não concluída).
     * - Adiciona a atividade na lista do aluno.
     * @param atividade Objeto Atividade a ser adicionado.
     * @return Atividade salva.
     */
    public Atividade addAtividade(Atividade atividade) {
        Aluno aluno = alunoService.findByAtividade(atividade);
        atividade.setStatus(Status.PENDENTE);
        atividade.setDataFim(null);
        aluno.adicionarAtividadeAluno(atividade);
        return atividadeRepository.save(atividade);
    }

    /**
     * Atualiza a descrição de uma atividade existente.
     * @param id ID da atividade a ser atualizada.
     * @param description Nova descrição.
     * @return Atividade atualizada.
     */
    public Atividade atualizarAtividade(Long id, String description) {
        Atividade atividadeAtualizada = findById(id);
        atividadeAtualizada.setDescricao(description);
        return atividadeRepository.save(atividadeAtualizada);
    }

    /**
     * Remove uma atividade pelo seu ID.
     * @param id ID da atividade a ser removida.
     */
    public void deleteById(Long id) {
        atividadeRepository.deleteById(id);
    }
}
