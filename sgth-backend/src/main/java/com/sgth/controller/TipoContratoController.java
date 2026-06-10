package com.sgth.controller;

import com.sgth.entity.TipoContrato;
import com.sgth.service.TipoContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-contrato")
@CrossOrigin(origins = "*")
public class TipoContratoController {

    private final TipoContratoService tipoContratoService;

    public TipoContratoController(TipoContratoService tipoContratoService) {
        this.tipoContratoService = tipoContratoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoContrato>> listarTodos() {
        return ResponseEntity.ok(tipoContratoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContrato> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoContratoService.buscarPorId(id));
    }
}
