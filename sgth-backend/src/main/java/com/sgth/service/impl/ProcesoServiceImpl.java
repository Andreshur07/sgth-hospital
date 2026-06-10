package com.sgth.service.impl;

import com.sgth.entity.Proceso;
import com.sgth.repository.ProcesoRepository;
import com.sgth.service.ProcesoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository procesoRepository;

    public ProcesoServiceImpl(ProcesoRepository procesoRepository) {
        this.procesoRepository = procesoRepository;
    }

    @Override
    public List<Proceso> listarTodos() {
        return procesoRepository.findAll();
    }

    @Override
    public Proceso buscarPorId(Integer id) {
        return procesoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Proceso no encontrado con id: " + id));
    }

    @Override
    public Proceso guardar(Proceso proceso) {

        if (proceso.getActivo() == null) {
            proceso.setActivo(true);
        }

        return procesoRepository.save(proceso);
    }

    @Override
    public Proceso actualizar(Integer id, Proceso proceso) {

        Proceso existente = buscarPorId(id);

        existente.setNombre(proceso.getNombre());
        existente.setActivo(proceso.getActivo());

        return procesoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {

        Proceso proceso = buscarPorId(id);

        proceso.setActivo(false);

        procesoRepository.save(proceso);
    }
}