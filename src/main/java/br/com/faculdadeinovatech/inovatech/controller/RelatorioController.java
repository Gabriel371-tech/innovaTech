package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Aluno;
import br.com.faculdadeinovatech.inovatech.service.AlunoService;
import br.com.faculdadeinovatech.inovatech.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("")
    public String index(Model model) {
        return "relatorio/relatorios";
    }

    @GetMapping("/alunos")
    public String relatorioAlunos(Model model) {
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("dataEmissao", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        return "relatorio/relatorioAlunos";
    }

    @GetMapping("/alunos/pdf")
    public void exportarAlunosPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=alunos_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Aluno> alunos = alunoService.findAll();

        relatorioService.exportarAlunosPdf(response, alunos);
    }

    @GetMapping("/aluno/{id}/pdf")
    public void exportarFichaAlunoPdf(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        
        Aluno aluno = alunoService.findById(id);
        if (aluno == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Aluno não encontrado");
            return;
        }

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ficha_aluno_" + aluno.getNomeAluno().replace(" ", "_") + ".pdf";
        response.setHeader(headerKey, headerValue);

        relatorioService.exportarFichaAlunoPdf(response, aluno);
    }
}
