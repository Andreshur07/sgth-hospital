package com.sgth.service;

import com.sgth.entity.Dependencia;

import java.util.List;

public interface DependenciaService {

    List<Dependencia> listarTodos();

    Dependencia buscarPorId(Integer id);
}
