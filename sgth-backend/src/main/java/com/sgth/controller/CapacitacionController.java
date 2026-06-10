package com.sgth.controller;

import com.sgth.entity.Capacitacion;
import com.sgth.service.CapacitacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capacitaciones")
@CrossOrigin(origins = "*")
public class CapacitacionController {

    private final CapacitacionService capacitacionService;

    public CapacitacionController(CapacitacionService capacitacionService) {
        this.capacitacionService = capacitacionService;
    }

    @GetMapping
    public ResponseEntity<List<Capacitacion>> listarTodos() {
        return ResponseEntity.ok(capacitacionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capacitacion> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(capacitacionService.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<Capacitacion>> listarPorFuncionario(@PathVariable Integer funcionarioId) {
        return ResponseEntity.ok(capacitacionService.listarPorFuncionario(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<Capacitacion> guardar(@RequestBody Capacitacion capacitacion) {
        return ResponseEntity.ok(capacitacionService.guardar(capacitacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Capacitacion> actualizar(
            @PathVariable Integer id,
            @RequestBody Capacitacion capacitacion
    ) {
        return ResponseEntity.ok(capacitacionService.actualizar(id, capacitacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        capacitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
