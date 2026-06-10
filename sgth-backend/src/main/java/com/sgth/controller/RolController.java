package com.sgth.controller;

import com.sgth.entity.Rol;
import com.sgth.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Rol> guardar(
            @RequestBody Rol rol) {
        return ResponseEntity.ok(service.guardar(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(
            @PathVariable Integer id,
            @RequestBody Rol rol) {
        return ResponseEntity.ok(service.actualizar(id, rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}