package com.jjo.rankingatividades.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjo.rankingatividades.domain.models.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
    Optional<List<Atividade>> findByAlunoId (Long alunoId);

}
