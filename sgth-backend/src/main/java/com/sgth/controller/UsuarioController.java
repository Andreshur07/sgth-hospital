package com.sgth.controller;

import com.sgth.entity.Usuario;
import com.sgth.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgth.dto.UsuarioResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<UsuarioResponseDTO>> listarResumen() {
        List<UsuarioResponseDTO> usuarios = service.listarTodos()
                .stream()
                .map(usuario -> UsuarioResponseDTO.builder()
                        .id(usuario.getId())
                        .username(usuario.getUsername())
                        .email(usuario.getEmail())
                        .activo(usuario.getActivo())
                        .nombreFuncionario(
                                usuario.getFuncionario() != null
                                        ? (
                                        (usuario.getFuncionario().getPrimerNombre() != null ? usuario.getFuncionario().getPrimerNombre() : "") + " " +
                                        (usuario.getFuncionario().getSegundoNombre() != null ? usuario.getFuncionario().getSegundoNombre() : "") + " " +
                                        (usuario.getFuncionario().getPrimerApellido() != null ? usuario.getFuncionario().getPrimerApellido() : "") + " " +
                                        (usuario.getFuncionario().getSegundoApellido() != null ? usuario.getFuncionario().getSegundoApellido() : "")
                                ).trim()
                                        : null
                        )
                        .build())
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable Integer id,
            @RequestBody Usuario usuario
    ) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}