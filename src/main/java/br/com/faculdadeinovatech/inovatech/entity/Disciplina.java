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
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDisciplina;

    @Column(nullable = false, length = 60)
    private String nomeDisciplina;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "idCurso_fk")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "idProfessor_fk")
    private Professor professor;
}