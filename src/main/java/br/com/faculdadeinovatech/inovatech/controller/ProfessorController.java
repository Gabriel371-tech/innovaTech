package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Professor;
import br.com.faculdadeinovatech.inovatech.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    // GET /professores — lista todos
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        List<Professor> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }

    // GET /professores/{id} — busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Integer id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /professores/cpf/{cpf} — busca por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Professor> buscarPorCpf(@PathVariable String cpf) {
        return professorService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /professores/nome?nome=... — busca por nome
    @GetMapping("/nome")
    public ResponseEntity<List<Professor>> buscarPorNome(@RequestParam String nome) {
        List<Professor> professores = professorService.buscarPorNome(nome);
        return ResponseEntity.ok(professores);
    }

    // GET /professores/curso/{idCurso} — busca por curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Professor>> buscarPorCurso(@PathVariable Integer idCurso) {
        List<Professor> professores = professorService.buscarPorCurso(idCurso);
        return ResponseEntity.ok(professores);
    }

    // POST /professores — cadastra novo professor
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Professor professor) {
        try {
            Professor salvo = professorService.salvar(professor);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // PUT /professores/{id} — atualiza professor
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Professor professor) {
        try {
            Professor atualizado = professorService.atualizar(id, professor);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /professores/{id} — remove professor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            professorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}