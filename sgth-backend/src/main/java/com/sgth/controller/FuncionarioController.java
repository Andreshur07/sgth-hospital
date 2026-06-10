package com.sgth.controller;

import com.sgth.entity.Funcionario;
import com.sgth.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgth.dto.FuncionarioResumenDTO;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<FuncionarioResumenDTO>> resumen() {
        return ResponseEntity.ok(funcionarioService.listarResumen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Funcionario> guardar(@RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(funcionarioService.guardar(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> actualizar(
            @PathVariable Integer id,
            @RequestBody Funcionario funcionario
    ) {
        return ResponseEntity.ok(funcionarioService.actualizar(id, funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        funcionarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}