package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Disciplina;
import br.com.faculdadeinovatech.inovatech.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    // GET /disciplinas — lista todas
    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodas() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }

    // GET /disciplinas/{id} — busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id) {
        return disciplinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /disciplinas/nome?nome=... — busca por nome
    @GetMapping("/nome")
    public ResponseEntity<List<Disciplina>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(disciplinaService.buscarPorNome(nome));
    }

    // GET /disciplinas/curso/{idCurso} — busca por curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Disciplina>> buscarPorCurso(@PathVariable Integer idCurso) {
        return ResponseEntity.ok(disciplinaService.buscarPorCurso(idCurso));
    }

    // GET /disciplinas/professor/{idProfessor} — busca por professor
    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<List<Disciplina>> buscarPorProfessor(@PathVariable Integer idProfessor) {
        return ResponseEntity.ok(disciplinaService.buscarPorProfessor(idProfessor));
    }

    // POST /disciplinas — cadastra nova disciplina
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Disciplina disciplina) {
        try {
            Disciplina salva = disciplinaService.salvar(disciplina);
            return ResponseEntity.status(HttpStatus.CREATED).body(salva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /disciplinas/{id} — atualiza disciplina
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Disciplina disciplina) {
        try {
            Disciplina atualizada = disciplinaService.atualizar(id, disciplina);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /disciplinas/{id} — remove disciplina
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            disciplinaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}