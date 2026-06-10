package com.sgth.controller;

import com.sgth.entity.TipoDocumento;
import com.sgth.service.TipoDocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-documento")
@CrossOrigin(origins = "*")
public class TipoDocumentoController {

    private final TipoDocumentoService service;

    public TipoDocumentoController(TipoDocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocumento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoDocumento> guardar(
            @RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(
                service.guardar(tipoDocumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumento> actualizar(
            @PathVariable Integer id,
            @RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(
                service.actualizar(id, tipoDocumento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}