package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Disciplina;
import br.com.faculdadeinovatech.inovatech.service.CursoService;
import br.com.faculdadeinovatech.inovatech.service.DisciplinaService;
import br.com.faculdadeinovatech.inovatech.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfessorService professorService;

    // GET /disciplinas/listar — lista todas
    @GetMapping("/listar")
    public String listarTodas(Model model) {
        model.addAttribute("disciplinas", disciplinaService.listarTodas());
        return "disciplina/listaDisciplinas";
    }

    // GET /disciplinas/criar — abre formulário de cadastro
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("cursos", cursoService.findAll());
        model.addAttribute("professores", professorService.listarTodos());
        return "disciplina/formularioDisciplina";
    }

    // POST /disciplinas/salvar — salva ou atualiza disciplina
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina) {
        if (disciplina.getIdDisciplina() != null) {
            disciplinaService.atualizar(disciplina.getIdDisciplina(), disciplina);
        } else {
            disciplinaService.salvar(disciplina);
        }
        return "redirect:/disciplinas/listar";
    }

    // GET /disciplinas/editar/{id} — abre formulário de edição
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Disciplina disciplina = disciplinaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("cursos", cursoService.findAll());
        model.addAttribute("professores", professorService.listarTodos());
        return "disciplina/formularioDisciplina";
    }

    // GET /disciplinas/excluir/{id} — remove disciplina
    @GetMapping("/excluir/{id}")
    public String deletar(@PathVariable Integer id) {
        disciplinaService.deletar(id);
        return "redirect:/disciplinas/listar";
    }
}