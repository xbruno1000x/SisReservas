package com.SisReservas.controller;

import com.SisReservas.model.Profissional;
import com.SisReservas.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
@CrossOrigin(origins = "*")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<List<Profissional>> listarTodos() {
        List<Profissional> profissionais = profissionalService.listarTodos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Profissional>> listarAtivos() {
        List<Profissional> profissionais = profissionalService.listarAtivos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        return profissionalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Profissional> buscarPorEmail(@PathVariable String email) {
        return profissionalService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<Profissional>> buscarPorEspecialidade(@PathVariable String especialidade) {
        List<Profissional> profissionais = profissionalService.buscarPorEspecialidade(especialidade);
        return ResponseEntity.ok(profissionais);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Profissional profissional) {
        try {
            Profissional novoProfissional = profissionalService.salvar(profissional);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProfissional);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Profissional profissional) {
        try {
            Profissional profissionalAtualizado = profissionalService.atualizar(id, profissional);
            return ResponseEntity.ok(profissionalAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/ativo")
    public ResponseEntity<?> ativarDesativar(@PathVariable Long id, @RequestParam Boolean ativo) {
        try {
            Profissional profissionalAtualizado = profissionalService.ativarDesativar(id, ativo);
            return ResponseEntity.ok(profissionalAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            profissionalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
