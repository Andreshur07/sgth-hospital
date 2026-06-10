package com.sgth.controller;

import com.sgth.entity.Alerta;
import com.sgth.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgth.dto.AlertaResumenDTO;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin("*")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Alerta>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<AlertaResumenDTO>> resumen() {
        return ResponseEntity.ok(service.listarResumen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<Alerta>> listarPorFuncionario(@PathVariable Integer funcionarioId) {
        return ResponseEntity.ok(service.listarPorFuncionario(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<Alerta> guardar(@RequestBody Alerta alerta) {
        return ResponseEntity.ok(service.guardar(alerta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerta> actualizar(
            @PathVariable Integer id,
            @RequestBody Alerta alerta
    ) {
        return ResponseEntity.ok(service.actualizar(id, alerta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}