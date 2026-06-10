package com.sgth.service.impl;

import com.sgth.dto.DashboardResumenDTO;
import com.sgth.repository.AlertaRepository;
import com.sgth.repository.DocumentoRepository;
import com.sgth.repository.FuncionarioRepository;
import com.sgth.repository.UsuarioRepository;
import com.sgth.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final FuncionarioRepository funcionarioRepository;
    private final DocumentoRepository documentoRepository;
    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;

    public DashboardServiceImpl(
            FuncionarioRepository funcionarioRepository,
            DocumentoRepository documentoRepository,
            AlertaRepository alertaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.documentoRepository = documentoRepository;
        this.alertaRepository = alertaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DashboardResumenDTO obtenerResumen() {

        Long totalFuncionarios = (long) funcionarioRepository.findAll().size();

        Long funcionariosActivos = funcionarioRepository.findAll()
                .stream()
                .filter(f -> "ACTIVO".equalsIgnoreCase(f.getEstado()))
                .count();

        Long funcionariosInactivos = funcionarioRepository.findAll()
                .stream()
                .filter(f -> "INACTIVO".equalsIgnoreCase(f.getEstado()))
                .count();

        Long totalDocumentos = (long) documentoRepository.findAll().size();

        Long documentosVigentes = documentoRepository.findAll()
                .stream()
                .filter(d -> "VIGENTE".equalsIgnoreCase(d.getEstado()))
                .count();

        Long documentosVencidos = documentoRepository.findAll()
                .stream()
                .filter(d -> "VENCIDO".equalsIgnoreCase(d.getEstado()))
                .count();

        Long alertasPendientes = alertaRepository.findAll()
                .stream()
                .filter(a -> "PENDIENTE".equalsIgnoreCase(a.getEstado()))
                .count();

        Long usuariosActivos = usuarioRepository.findAll()
                .stream()
                .filter(u -> Boolean.TRUE.equals(u.getActivo()))
                .count();

        return new DashboardResumenDTO(
                totalFuncionarios,
                funcionariosActivos,
                funcionariosInactivos,
                totalDocumentos,
                documentosVigentes,
                documentosVencidos,
                alertasPendientes,
                usuariosActivos
        );
    }
}