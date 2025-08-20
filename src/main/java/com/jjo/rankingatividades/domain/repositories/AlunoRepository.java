package com.jjo.rankingatividades.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jjo.rankingatividades.domain.models.Aluno;

@Repository // Isso indica que essa interface é um repositório do JPA
public interface AlunoRepository extends JpaRepository<Aluno, Long> , AlunoRepositoryQueries {

    List<Aluno> findAllByName (String name) ;
    List<Aluno> findAllByNamePageable (int pageNumber , int pageSize , String name) ;



    //    Essa função vai procurar um aluno pelo email e retornar nulo ou o aluno
    Optional<Aluno> findByEmail(String email) ;
    Optional<Aluno> findByName(String email) ;

}
