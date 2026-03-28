package br.com.faculdadeinovatech.inovatech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech.entity.Curso;
import br.com.faculdadeinovatech.inovatech.repository.CursoRepository;

@Service
public class CursoService {

    //Injeção de dependência do repositório de cursos
    @Autowired
    private CursoRepository cursoRepository;

    //Método para salvar um curso
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    //Método para listar todos os cursos
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    //Método para encontrar um curso por ID
    public Curso findById(Integer id) {
        return cursoRepository.findById(id).orElse(null);
    }

    //Método para excluir um curso por ID
    public void deleteById(Integer id) {
        cursoRepository.deleteById(id);
    }
    
}
