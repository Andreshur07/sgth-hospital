package com.sgth.controller;

import com.sgth.entity.Subproceso;
import com.sgth.service.SubprocesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subprocesos")
@CrossOrigin("*")
public class SubprocesoController {

    private final SubprocesoService service;

    public SubprocesoController(SubprocesoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Subproceso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subproceso> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/proceso/{procesoId}")
    public ResponseEntity<List<Subproceso>> listarPorProceso(@PathVariable Integer procesoId) {
        return ResponseEntity.ok(service.listarPorProceso(procesoId));
    }

    @PostMapping
    public ResponseEntity<Subproceso> guardar(@RequestBody Subproceso subproceso) {
        return ResponseEntity.ok(service.guardar(subproceso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subproceso> actualizar(
            @PathVariable Integer id,
            @RequestBody Subproceso subproceso
    ) {
        return ResponseEntity.ok(service.actualizar(id, subproceso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}