package com.jjo.rankingatividades.domain.repositories;

import com.jjo.rankingatividades.domain.models.Aluno;

import java.util.List;

public interface AlunoRepositoryQueries {

    List<Aluno> findAllByNamePageable (int pageNumber , int pageSize , String name) ;
    List<Aluno> findAllByName (String name) ;


}
