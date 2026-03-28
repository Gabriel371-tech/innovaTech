package br.com.faculdadeinovatech.inovatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    

}
