package com.sgth.controller;

import com.sgth.entity.Documento;
import com.sgth.service.DocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgth.dto.DocumentoResumenDTO;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin("*")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Documento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<Documento>> listarPorFuncionario(
            @PathVariable Integer funcionarioId) {
        return ResponseEntity.ok(service.listarPorFuncionario(funcionarioId));
    }

    @GetMapping("/resumen")
    public List<DocumentoResumenDTO> resumen() {
        return service.listarResumen();
    }

    @PostMapping
    public ResponseEntity<Documento> guardar(@RequestBody Documento documento) {
        return ResponseEntity.ok(service.guardar(documento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(
            @PathVariable Integer id,
            @RequestBody Documento documento) {
        return ResponseEntity.ok(service.actualizar(id, documento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}