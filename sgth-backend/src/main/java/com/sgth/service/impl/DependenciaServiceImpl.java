package com.sgth.service.impl;

import com.sgth.entity.Dependencia;
import com.sgth.repository.DependenciaRepository;
import com.sgth.service.DependenciaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenciaServiceImpl implements DependenciaService {

    private final DependenciaRepository dependenciaRepository;

    public DependenciaServiceImpl(DependenciaRepository dependenciaRepository) {
        this.dependenciaRepository = dependenciaRepository;
    }

    @Override
    public List<Dependencia> listarTodos() {
        return dependenciaRepository.findAll();
    }

    @Override
    public Dependencia buscarPorId(Integer id) {
        return dependenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
    }
}