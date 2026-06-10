package com.sgth.service.impl;

import com.sgth.entity.Subproceso;
import com.sgth.repository.SubprocesoRepository;
import com.sgth.service.SubprocesoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprocesoServiceImpl implements SubprocesoService {

    private final SubprocesoRepository repository;

    public SubprocesoServiceImpl(SubprocesoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Subproceso> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Subproceso buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subproceso no encontrado"));
    }

    @Override
    public List<Subproceso> listarPorProceso(Integer procesoId) {
        return repository.findByProcesoId(procesoId);
    }

    @Override
    public Subproceso guardar(Subproceso subproceso) {
        if (subproceso.getActivo() == null) {
            subproceso.setActivo(true);
        }
        return repository.save(subproceso);
    }

    @Override
    public Subproceso actualizar(Integer id, Subproceso subproceso) {
        Subproceso existente = buscarPorId(id);

        existente.setProceso(subproceso.getProceso());
        existente.setNombre(subproceso.getNombre());
        existente.setActivo(subproceso.getActivo());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Subproceso subproceso = buscarPorId(id);
        subproceso.setActivo(false);
        repository.save(subproceso);
    }
}