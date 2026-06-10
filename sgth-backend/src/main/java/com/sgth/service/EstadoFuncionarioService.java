package com.sgth.service;

import com.sgth.entity.EstadoFuncionario;

import java.util.List;

public interface EstadoFuncionarioService {

    List<EstadoFuncionario> listarTodos();

    EstadoFuncionario buscarPorId(Integer id);
}