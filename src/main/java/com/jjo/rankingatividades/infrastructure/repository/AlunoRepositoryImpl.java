package com.jjo.rankingatividades.infrastructure.repository;

import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.domain.repositories.AlunoRepository;
import com.jjo.rankingatividades.domain.repositories.AlunoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepositoryImpl implements AlunoRepositoryQueries {


//    Chamo a classe padrão do JPA que faz os métodos do banco na integra (o @PersistenceContext é obrigatório)
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private AlunoRepository alunoRepository;

    @Override
    public List<Aluno> findAllByName(String name) {

//        Inicia a "fabrica" do criteria
        CriteriaBuilder builder = manager.getCriteriaBuilder() ;

//        Instancia um novo query para fazer o JPQL personalizado
        CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class) ;

//        Pega a instância do objeto que estamos trabalhando, nesse caso, ele pode acessar as informações da classe de restaurante
        Root<Aluno> root = criteria.from(Aluno.class);

//        Crio uma lista de predicados/filtros pro meu query
        var predicates = new ArrayList<Predicate>();

//        Criamos os predicados para passar de parâmetro no where
        if (StringUtils.hasLength(name)) {
            Predicate nomePredicate = builder
                    .like(root.get("name") , "%" + name + "%");
            predicates.add(nomePredicate);
        }

//        Recebe predicados que são os parâmetros que passamos junto do where no JPQL (like)
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Aluno> query = manager.createQuery(criteria) ;
        return query.getResultList();

    }
    @Override
    public List<Aluno> findAllByNamePageable(int pageNumber , int pageSize , String name) {

//        Inicia a "fabrica" do criteria
        CriteriaBuilder builder = manager.getCriteriaBuilder() ;

//        Instancia um novo query para fazer o JPQL personalizado
        CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class) ;

//        Pega a instância do objeto que estamos trabalhando, nesse caso, ele pode acessar as informações da classe de restaurante
        Root<Aluno> root = criteria.from(Aluno.class);

//        Crio uma lista de predicados/filtros pro meu query
        var predicates = new ArrayList<Predicate>();

//        Criamos os predicados para passar de parâmetro no where
        if (StringUtils.hasLength(name)) {
            Predicate nomePredicate = builder
                    .like(root.get("name") , "%" + name + "%");
            predicates.add(nomePredicate);
        }

//        Recebe predicados que são os parâmetros que passamos junto do where no JPQL (like)
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Aluno> query = manager.createQuery(criteria) ;

        int offset = pageNumber * pageSize;

        query.setFirstResult(offset);
        query.setMaxResults(pageSize);

        return query.getResultList();

    }

}
