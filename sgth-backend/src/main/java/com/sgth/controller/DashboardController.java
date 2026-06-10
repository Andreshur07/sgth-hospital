package com.sgth.controller;

import com.sgth.dto.DashboardResumenDTO;
import com.sgth.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/resumen")
    public ResponseEntity<DashboardResumenDTO> resumen() {
        return ResponseEntity.ok(service.obtenerResumen());
    }
}