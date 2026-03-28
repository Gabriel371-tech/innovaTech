package br.com.faculdadeinovatech.inovatech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech.entity.Aluno;
import br.com.faculdadeinovatech.inovatech.repository.AlunoRepository;

@Service
public class AlunoService {

    //Injeção de dependência do repositório de alunos
    @Autowired
    private AlunoRepository alunoRepository;

    //Método para salvar um aluno
    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    //Método para listar todos os alunos
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    //Método para encontrar um aluno por ID
    public Aluno findById(Integer id) {
        return alunoRepository.findById(id).orElse(null);
    }

    //Método para excluir um aluno por ID
    public void deleteById(Integer id) {
        alunoRepository.deleteById(id);
    }
    
}
