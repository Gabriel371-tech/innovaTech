package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Professor;
import br.com.faculdadeinovatech.inovatech.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    // GET /professores/listar — lista todos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Professor> professores = professorService.listarTodos();
        model.addAttribute("professores", professores);
        return "professor/listaProfessores";
    }

    // GET /professores/criar — abre formulário de cadastro
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("cursos", cursoService.findAll());
        return "professor/formularioProfessor";
    }

    // POST /professores/salvar — salva ou atualiza professor
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Professor professor) {
        if (professor.getIdProfessor() != null) {
            professorService.atualizar(professor.getIdProfessor(), professor);
        } else {
            professorService.salvar(professor);
        }
        return "redirect:/professores/listar";
    }

    // GET /professores/editar/{id} — abre formulário de edição
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Professor professor = professorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        model.addAttribute("professor", professor);
        model.addAttribute("cursos", cursoService.findAll());
        return "professor/formularioProfessor";
    }

    // GET /professores/excluir/{id} — remove professor
    @GetMapping("/excluir/{id}")
    public String deletar(@PathVariable Integer id) {
        professorService.deletar(id);
        return "redirect:/professores/listar";
    }
}