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
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAluno;

    @Column(nullable = false, length = 40)
    private String nomeAluno;

    @Column(length = 30)
    private String emailAluno;

    @Column(nullable = false, length = 15)
    private String telefoneAluno;
    
    @Column(nullable = false, length = 11)
    private String cpfAluno;

    @Column(nullable = false, length = 20)
    private String cidadeAluno;
    
    @Column(nullable = false, length = 100)
    private String enderecoAluno;

    @ManyToOne
    @JoinColumn(name = "idCurso_fk")
    private Curso curso;
}
