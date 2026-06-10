package com.sgth.controller;

import com.sgth.entity.Proceso;
import com.sgth.service.ProcesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin("*")
public class ProcesoController {

    private final ProcesoService procesoService;

    public ProcesoController(ProcesoService procesoService) {
        this.procesoService = procesoService;
    }

    @GetMapping
    public ResponseEntity<List<Proceso>> listar() {
        return ResponseEntity.ok(procesoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proceso> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(procesoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Proceso> guardar(@RequestBody Proceso proceso) {
        return ResponseEntity.ok(procesoService.guardar(proceso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proceso> actualizar(
            @PathVariable Integer id,
            @RequestBody Proceso proceso) {

        return ResponseEntity.ok(
                procesoService.actualizar(id, proceso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        procesoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}