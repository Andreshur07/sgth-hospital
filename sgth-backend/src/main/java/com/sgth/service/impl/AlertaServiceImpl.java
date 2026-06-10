package com.sgth.service.impl;

import com.sgth.entity.Alerta;
import com.sgth.repository.AlertaRepository;
import com.sgth.service.AlertaService;
import org.springframework.stereotype.Service;
import com.sgth.dto.AlertaResumenDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaServiceImpl implements AlertaService {

    private final AlertaRepository repository;

    public AlertaServiceImpl(AlertaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Alerta> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Alerta buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
    }

    @Override
    public List<Alerta> listarPorFuncionario(Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public List<AlertaResumenDTO> listarResumen() {
        return repository.findAll()
                .stream()
                .map(alerta -> new AlertaResumenDTO(
                        alerta.getId(),
                        (
                                (alerta.getFuncionario().getPrimerNombre() != null ? alerta.getFuncionario().getPrimerNombre() : "") + " " +
                                        (alerta.getFuncionario().getSegundoNombre() != null ? alerta.getFuncionario().getSegundoNombre() : "") + " " +
                                        (alerta.getFuncionario().getPrimerApellido() != null ? alerta.getFuncionario().getPrimerApellido() : "") + " " +
                                        (alerta.getFuncionario().getSegundoApellido() != null ? alerta.getFuncionario().getSegundoApellido() : "")
                        ).trim(),
                        alerta.getTipo(),
                        alerta.getFechaAlerta() != null ? alerta.getFechaAlerta().toString() : null,
                        alerta.getEstado()
                ))
                .toList();
    }

    @Override
    public Alerta guardar(Alerta alerta) {
        alerta.setCreadoEn(LocalDateTime.now());

        if (alerta.getEstado() == null || alerta.getEstado().isBlank()) {
            alerta.setEstado("PENDIENTE");
        }

        return repository.save(alerta);
    }

    @Override
    public Alerta actualizar(Integer id, Alerta alerta) {
        Alerta existente = buscarPorId(id);

        existente.setFuncionario(alerta.getFuncionario());
        existente.setTipo(alerta.getTipo());
        existente.setMensaje(alerta.getMensaje());
        existente.setFechaAlerta(alerta.getFechaAlerta());
        existente.setEstado(alerta.getEstado());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Alerta alerta = buscarPorId(id);
        alerta.setEstado("INACTIVA");
        repository.save(alerta);
    }
}