package com.jjo.rankingatividades.domain.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id ;

    // TODO: Continuar com verificações no email, nome e data de nascimento. Mesmo que eu já tenha uma classe de validações, creio que também seja necesário anotações do JPA validation

    @NotBlank
    protected String name ;

    @NotBlank
    @Email
    protected String email ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected LocalDate dataNascimento ;

    //    mappedBy indica que é o elemento "aluno" que faz essa relação, afinal um aluno pode ter várias atividades (ManyToOne) porém somente UM aluno pode ter essas atividades (OneToMany). O padrão é "aluno"
    //    cascade = "CascadeType.ALL" indica que toda mudança que ocorrer dentro dessa lista deve ser sincronizada com a mudança no banco de dados, uma vez que, quando modificada a lista de autuações adicionando uma nova, nós usamos uma função da classe de aluno, e não incluimos nada sobre a entidade de Atividades, ou seja, o Jakarta não sabe onde mudar. Quando adicionamos essa anotação ele entende que assim que houver mudança, ele deve sincronizar com a tabela ligada à Classe original de Atividades.
    @OneToMany(mappedBy = "aluno" , cascade = CascadeType.ALL ,orphanRemoval = true)
    protected List<Atividade> atividades = new ArrayList<>();

    public Usuario(String name , String email, LocalDate dataNascimento) {
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.atividades = new ArrayList<>();
    }

    public Usuario(Long id, String name , String email, LocalDate dataNascimento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.atividades = new ArrayList<>();
    }


    
    

    


}
