package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Professor;
import br.com.faculdadeinovatech.inovatech.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    // Listar todos os professores
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    // Buscar professor por ID
    public Optional<Professor> buscarPorId(Integer id) {
        return professorRepository.findById(id);
    }

    // Buscar professor por CPF
    public Optional<Professor> buscarPorCpf(String cpf) {
        return professorRepository.findByCpfProfessor(cpf);
    }

    // Buscar professores por nome (parcial, case-insensitive)
    public List<Professor> buscarPorNome(String nome) {
        return professorRepository.findByNomeProfessorContainingIgnoreCase(nome);
    }

    // Buscar professores por curso
    public List<Professor> buscarPorCurso(Integer idCurso) {
        return professorRepository.findByCurso_IdCurso(idCurso);
    }

    // Salvar novo professor
    public Professor salvar(Professor professor) {
        // Valida se já existe professor com o mesmo CPF
        Optional<Professor> existente = professorRepository.findByCpfProfessor(professor.getCpfProfessor());
        if (existente.isPresent()) {
            throw new RuntimeException("Já existe um professor cadastrado com o CPF: " + professor.getCpfProfessor());
        }
        return professorRepository.save(professor);
    }

    // Atualizar professor existente
    public Professor atualizar(Integer id, Professor professorAtualizado) {
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));

        professorExistente.setNomeProfessor(professorAtualizado.getNomeProfessor());
        professorExistente.setEmailProfessor(professorAtualizado.getEmailProfessor());
        professorExistente.setTelefoneProfessor(professorAtualizado.getTelefoneProfessor());
        professorExistente.setCpfProfessor(professorAtualizado.getCpfProfessor());
        professorExistente.setCidadeProfessor(professorAtualizado.getCidadeProfessor());
        professorExistente.setEnderecoProfessor(professorAtualizado.getEnderecoProfessor());
        professorExistente.setEspecialidade(professorAtualizado.getEspecialidade());
        professorExistente.setCurso(professorAtualizado.getCurso());

        return professorRepository.save(professorExistente);
    }

    public void deletar(Integer id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));
        professorRepository.delete(professor);
    }
}