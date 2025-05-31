package com.jjo.rankingatividades.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.jjo.rankingatividades.domain.DTOs.AlunoDTO;
import com.jjo.rankingatividades.domain.models.Aluno;
import com.jjo.rankingatividades.models.AlunoRepresentation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
// Nós precisamos usar a anotação componente, por que toda vez que for ser usada em algum lugar, o spring automaticamente vai injetar todas as dependências, nessse caso, a principal delas é o modelMapper, que nós configuramos na pasta /config, o spring cuida de instanciar tudo e já intetar nas classes que usam esta!
// Quando usamos isso, o spring inicia a classe antes mesmo de ser chamada, o que impede de carregar um componente que usa ela antes dela existir!
@Component
public class AlunoAssembler {

    private final ModelMapper modelMapper;

    public AlunoRepresentation toModel (Aluno aluno) {
        return modelMapper.map(aluno , AlunoRepresentation.class);
    }
    
    public Aluno toEntity (AlunoDTO alunoDTO) {
        return new Aluno(alunoDTO.getName() , alunoDTO.getEmail() , alunoDTO.getDataNascimento(),alunoDTO.getClasse() , alunoDTO.getCurso()) ;
    }
    
    public List<AlunoRepresentation> toCollection(List<Aluno> alunos) {
        return alunos.stream().map(this :: toModel).toList();
    }

}
