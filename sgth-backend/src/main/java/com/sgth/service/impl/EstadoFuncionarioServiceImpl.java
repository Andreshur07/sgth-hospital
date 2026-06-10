package com.sgth.service.impl;

import com.sgth.entity.EstadoFuncionario;
import com.sgth.repository.EstadoFuncionarioRepository;
import com.sgth.service.EstadoFuncionarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoFuncionarioServiceImpl implements EstadoFuncionarioService {

    private final EstadoFuncionarioRepository estadoFuncionarioRepository;

    public EstadoFuncionarioServiceImpl(EstadoFuncionarioRepository estadoFuncionarioRepository) {
        this.estadoFuncionarioRepository = estadoFuncionarioRepository;
    }

    @Override
    public List<EstadoFuncionario> listarTodos() {
        return estadoFuncionarioRepository.findAll();
    }

    @Override
    public EstadoFuncionario buscarPorId(Integer id) {
        return estadoFuncionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de funcionario no encontrado"));
    }
}
