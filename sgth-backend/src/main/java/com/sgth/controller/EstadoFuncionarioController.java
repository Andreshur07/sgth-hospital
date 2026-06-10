package com.sgth.controller;

import com.sgth.entity.EstadoFuncionario;
import com.sgth.service.EstadoFuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados-funcionario")
@CrossOrigin(origins = "*")
public class EstadoFuncionarioController {

    private final EstadoFuncionarioService estadoFuncionarioService;

    public EstadoFuncionarioController(EstadoFuncionarioService estadoFuncionarioService) {
        this.estadoFuncionarioService = estadoFuncionarioService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoFuncionario>> listarTodos() {
        return ResponseEntity.ok(estadoFuncionarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoFuncionario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoFuncionarioService.buscarPorId(id));
    }
}
