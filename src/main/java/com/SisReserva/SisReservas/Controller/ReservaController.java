package com.SisReservas.controller;

import com.SisReservas.model.Reserva;
import com.SisReservas.model.Reserva.StatusReserva;
import com.SisReservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        return ResponseEntity.ok(reservas);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Reserva>> buscarComFiltros(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long profissionalId) {
        List<Reserva> reservas = reservaService.buscarComFiltros(data, clienteId, profissionalId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Reserva>> buscarPorCliente(@PathVariable Long clienteId) {
        List<Reserva> reservas = reservaService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Reserva>> buscarPorProfissional(@PathVariable Long profissionalId) {
        List<Reserva> reservas = reservaService.buscarPorProfissional(profissionalId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<Reserva>> buscarPorData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Reserva> reservas = reservaService.buscarPorData(data);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> buscarPorStatus(@PathVariable StatusReserva status) {
        List<Reserva> reservas = reservaService.buscarPorStatus(status);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Reserva>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<Reserva> reservas = reservaService.buscarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Reserva reserva) {
        try {
            Reserva novaReserva = reservaService.criar(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Reserva reservaAtualizada = reservaService.atualizar(id, reserva);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam StatusReserva status) {
        try {
            Reserva reservaAtualizada = reservaService.atualizarStatus(id, status);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            reservaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
