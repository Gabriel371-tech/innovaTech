package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.service.CursoService;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdadeinovatech.inovatech.entity.Aluno;
import br.com.faculdadeinovatech.inovatech.service.AlunoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/alunos")
public class AlunoController {

    //Injeção de dependência do serviço de alunos
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;


    //Método para salvar um aluno

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        alunoService.save(aluno);
        return "redirect:/alunos/listar";
    }

    //Método para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunos";
    }
    
    //Método para abrir o formulário de cadastro de aluno

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoService.findAll());

        return "aluno/formularioAluno";
    }
    
    // Método para abrir o formulário de edição de aluno
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoService.findById(id);
        model.addAttribute("aluno", aluno);
        model.addAttribute("cursos", cursoService.findAll());
        return "aluno/formularioAluno";
    }

    //Método para excluir um aluno
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        alunoService.deleteById(id);
        return "redirect:/alunos/listar";
    }
    
}
