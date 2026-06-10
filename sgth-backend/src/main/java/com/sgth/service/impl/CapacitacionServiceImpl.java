package com.sgth.service.impl;

import com.sgth.entity.Capacitacion;
import com.sgth.repository.CapacitacionRepository;
import com.sgth.service.CapacitacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

    private final CapacitacionRepository capacitacionRepository;

    public CapacitacionServiceImpl(CapacitacionRepository capacitacionRepository) {
        this.capacitacionRepository = capacitacionRepository;
    }

    @Override
    public List<Capacitacion> listarTodos() {
        return capacitacionRepository.findAll();
    }

    @Override
    public Capacitacion buscarPorId(Integer id) {
        return capacitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Capacitación no encontrada con id: " + id));
    }

    @Override
    public List<Capacitacion> listarPorFuncionario(Integer funcionarioId) {
        return capacitacionRepository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public Capacitacion guardar(Capacitacion capacitacion) {
        if (capacitacion.getEstado() == null || capacitacion.getEstado().isBlank()) {
            capacitacion.setEstado("VIGENTE");
        }
        return capacitacionRepository.save(capacitacion);
    }

    @Override
    public Capacitacion actualizar(Integer id, Capacitacion capacitacion) {
        Capacitacion existente = buscarPorId(id);

        existente.setFuncionario(capacitacion.getFuncionario());
        existente.setNombre(capacitacion.getNombre());
        existente.setInstitucion(capacitacion.getInstitucion());
        existente.setFechaInicio(capacitacion.getFechaInicio());
        existente.setFechaVencimiento(capacitacion.getFechaVencimiento());
        existente.setDocumentoSoporte(capacitacion.getDocumentoSoporte());
        existente.setEstado(capacitacion.getEstado());

        return capacitacionRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Capacitacion capacitacion = buscarPorId(id);
        capacitacion.setEstado("INACTIVO");
        capacitacionRepository.save(capacitacion);
    }
}
