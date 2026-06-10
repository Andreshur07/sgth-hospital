package com.sgth.service.impl;

import com.sgth.entity.HistorialEstado;
import com.sgth.repository.HistorialEstadoRepository;
import com.sgth.service.HistorialEstadoService;
import org.springframework.stereotype.Service;
import com.sgth.dto.HistorialEstadoResumenDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialEstadoServiceImpl implements HistorialEstadoService {

    private final HistorialEstadoRepository repository;

    public HistorialEstadoServiceImpl(HistorialEstadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HistorialEstado> listarTodos() {
        return repository.findAll();
    }

    @Override
    public HistorialEstado buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial de estado no encontrado"));
    }

    @Override
    public List<HistorialEstado> listarPorFuncionario(Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public List<HistorialEstadoResumenDTO> listarResumen() {

        return repository.findAll()
                .stream()
                .map(historial -> new HistorialEstadoResumenDTO(
                        historial.getId(),
                        (
                                (historial.getFuncionario().getPrimerNombre() != null ? historial.getFuncionario().getPrimerNombre() : "") + " " +
                                        (historial.getFuncionario().getSegundoNombre() != null ? historial.getFuncionario().getSegundoNombre() : "") + " " +
                                        (historial.getFuncionario().getPrimerApellido() != null ? historial.getFuncionario().getPrimerApellido() : "") + " " +
                                        (historial.getFuncionario().getSegundoApellido() != null ? historial.getFuncionario().getSegundoApellido() : "")
                        ).trim(),
                        historial.getEstadoAnterior(),
                        historial.getEstadoNuevo(),
                        historial.getFecha().toString()
                ))
                .toList();
    }

    @Override
    public HistorialEstado guardar(HistorialEstado historialEstado) {
        if (historialEstado.getFecha() == null) {
            historialEstado.setFecha(LocalDateTime.now());
        }
        return repository.save(historialEstado);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}