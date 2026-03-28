package br.com.faculdadeinovatech.inovatech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProfessor;

    @Column(nullable = false, length = 40)
    private String nomeProfessor;

    @Column(length = 30)
    private String emailProfessor;

    @Column(nullable = false, length = 15)
    private String telefoneProfessor;

    @Column(nullable = false, length = 11)
    private String cpfProfessor;

    @Column(nullable = false, length = 20)
    private String cidadeProfessor;

    @Column(nullable = false, length = 100)
    private String enderecoProfessor;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "idCurso_fk")
    private Curso curso;
}