package com.jjo.rankingatividades.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjo.rankingatividades.domain.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    Optional<Aluno> findByEmail(String email) ;
    Optional<Aluno> findByName(String email) ;

}
