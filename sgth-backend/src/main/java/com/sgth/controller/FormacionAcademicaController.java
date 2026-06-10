package com.sgth.controller;

import com.sgth.entity.FormacionAcademica;
import com.sgth.service.FormacionAcademicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formaciones-academicas")
@CrossOrigin(origins = "*")
public class FormacionAcademicaController {

    private final FormacionAcademicaService service;

    public FormacionAcademicaController(
            FormacionAcademicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FormacionAcademica>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormacionAcademica> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<FormacionAcademica>>
    buscarPorFuncionario(@PathVariable Integer funcionarioId) {
        return ResponseEntity.ok(
                service.buscarPorFuncionario(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<FormacionAcademica> guardar(
            @RequestBody FormacionAcademica formacionAcademica) {
        return ResponseEntity.ok(
                service.guardar(formacionAcademica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormacionAcademica> actualizar(
            @PathVariable Integer id,
            @RequestBody FormacionAcademica formacionAcademica) {
        return ResponseEntity.ok(
                service.actualizar(id, formacionAcademica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
