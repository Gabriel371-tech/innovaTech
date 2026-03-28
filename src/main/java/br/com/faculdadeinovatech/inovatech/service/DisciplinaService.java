package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Disciplina;
import br.com.faculdadeinovatech.inovatech.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Listar todas as disciplinas
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    // Buscar disciplina por ID
    public Optional<Disciplina> buscarPorId(Integer id) {
        return disciplinaRepository.findById(id);
    }

    // Buscar disciplinas por nome (parcial, case-insensitive)
    public List<Disciplina> buscarPorNome(String nome) {
        return disciplinaRepository.findByNomeDisciplinaContainingIgnoreCase(nome);
    }

    // Buscar disciplinas por curso
    public List<Disciplina> buscarPorCurso(Integer idCurso) {
        return disciplinaRepository.findByCurso_IdCurso(idCurso);
    }

    // Buscar disciplinas por professor
    public List<Disciplina> buscarPorProfessor(Integer idProfessor) {
        return disciplinaRepository.findByProfessor_IdProfessor(idProfessor);
    }

    // Salvar nova disciplina
    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    // Atualizar disciplina existente
    public Disciplina atualizar(Integer id, Disciplina disciplinaAtualizada) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o ID: " + id));

        disciplinaExistente.setNomeDisciplina(disciplinaAtualizada.getNomeDisciplina());
        disciplinaExistente.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());
        disciplinaExistente.setCurso(disciplinaAtualizada.getCurso());
        disciplinaExistente.setProfessor(disciplinaAtualizada.getProfessor());

        return disciplinaRepository.save(disciplinaExistente);
    }

    // Deletar disciplina por ID
    public void deletar(Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o ID: " + id));
        disciplinaRepository.delete(disciplina);
    }
}
