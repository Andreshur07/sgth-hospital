package com.sgth.service;

import com.sgth.entity.HistorialEstado;

import java.util.List;

import com.sgth.dto.HistorialEstadoResumenDTO;

public interface HistorialEstadoService {

    List<HistorialEstado> listarTodos();

    HistorialEstado buscarPorId(Integer id);

    List<HistorialEstadoResumenDTO> listarResumen();

    List<HistorialEstado> listarPorFuncionario(Integer funcionarioId);

    HistorialEstado guardar(HistorialEstado historialEstado);

    void eliminar(Integer id);
}