package br.com.faculdadeinovatech.inovatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    

}
