package com.sgth.controller;

import com.sgth.entity.Dependencia;
import com.sgth.service.DependenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dependencias")
@CrossOrigin(origins = "*")
public class DependenciaController {

    private final DependenciaService dependenciaService;

    public DependenciaController(DependenciaService dependenciaService) {
        this.dependenciaService = dependenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Dependencia>> listarTodos() {
        return ResponseEntity.ok(dependenciaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dependencia> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(dependenciaService.buscarPorId(id));
    }
}
