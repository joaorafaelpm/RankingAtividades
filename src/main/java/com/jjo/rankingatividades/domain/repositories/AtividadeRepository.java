package com.jjo.rankingatividades.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjo.rankingatividades.domain.models.Atividade;
import org.springframework.stereotype.Repository;


@Repository // Isso indica que essa interface é um repositório do JPA
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

    

}
