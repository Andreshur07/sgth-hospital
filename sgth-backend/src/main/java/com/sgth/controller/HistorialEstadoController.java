package com.sgth.controller;

import com.sgth.entity.HistorialEstado;
import com.sgth.service.HistorialEstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgth.dto.HistorialEstadoResumenDTO;

import java.util.List;

@RestController
@RequestMapping("/api/historial-estados")
@CrossOrigin("*")
public class HistorialEstadoController {

    private final HistorialEstadoService service;

    public HistorialEstadoController(HistorialEstadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HistorialEstado>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialEstado> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<HistorialEstado>> listarPorFuncionario(
            @PathVariable Integer funcionarioId) {
        return ResponseEntity.ok(service.listarPorFuncionario(funcionarioId));
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<HistorialEstadoResumenDTO>> resumen() {
        return ResponseEntity.ok(service.listarResumen());
    }

    @PostMapping
    public ResponseEntity<HistorialEstado> guardar(@RequestBody HistorialEstado historialEstado) {
        return ResponseEntity.ok(service.guardar(historialEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}