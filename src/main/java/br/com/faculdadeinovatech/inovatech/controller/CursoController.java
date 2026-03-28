package br.com.faculdadeinovatech.inovatech.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdadeinovatech.inovatech.entity.Curso;
import br.com.faculdadeinovatech.inovatech.service.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/cursos")
public class CursoController {

    //Injeção de dependência do serviço de cursos
    @Autowired
    private CursoService cursoService;

    //Método para salvar um curso

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos/listar";
    }

    //Método para listar todos os cursos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }
    
    //Método para abrir o formulário de cadastro de curso

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formularioCurso";
    }

    //Método para abrir o formulário de edição de um curso
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Curso curso = cursoService.findById(id);
        model.addAttribute("curso", curso);
        return "curso/formularioCurso";
    }

    //Método para deletar um curso
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        cursoService.deleteById(id);
        return "redirect:/cursos/listar";
    }
}
