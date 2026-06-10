package com.sgth.controller;

import com.sgth.entity.RequisitoLegal;
import com.sgth.service.RequisitoLegalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requisitos-legales")
@CrossOrigin("*")
public class RequisitoLegalController {

    private final RequisitoLegalService service;

    public RequisitoLegalController(RequisitoLegalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RequisitoLegal>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisitoLegal> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<RequisitoLegal>> listarPorFuncionario(
            @PathVariable Integer funcionarioId) {

        return ResponseEntity.ok(service.listarPorFuncionario(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<RequisitoLegal> guardar(
            @RequestBody RequisitoLegal requisito) {

        return ResponseEntity.ok(service.guardar(requisito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequisitoLegal> actualizar(
            @PathVariable Integer id,
            @RequestBody RequisitoLegal requisito) {

        return ResponseEntity.ok(
                service.actualizar(id, requisito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}