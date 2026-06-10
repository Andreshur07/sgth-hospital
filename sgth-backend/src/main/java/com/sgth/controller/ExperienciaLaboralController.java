package com.sgth.controller;

import com.sgth.entity.ExperienciaLaboral;
import com.sgth.service.ExperienciaLaboralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias-laborales")
@CrossOrigin(origins = "*")
public class ExperienciaLaboralController {

    private final ExperienciaLaboralService service;

    public ExperienciaLaboralController(
            ExperienciaLaboralService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaLaboral>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<ExperienciaLaboral>>
    listarPorFuncionario(
            @PathVariable Integer funcionarioId) {

        return ResponseEntity.ok(
                service.listarPorFuncionario(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<ExperienciaLaboral> guardar(
            @RequestBody ExperienciaLaboral experiencia) {

        return ResponseEntity.ok(
                service.guardar(experiencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> actualizar(
            @PathVariable Integer id,
            @RequestBody ExperienciaLaboral experiencia) {

        return ResponseEntity.ok(
                service.actualizar(id, experiencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}