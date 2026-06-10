package com.sgth.service;

import com.sgth.entity.Alerta;
import com.sgth.dto.AlertaResumenDTO;

import java.util.List;

public interface AlertaService {

    List<Alerta> listarTodos();

    Alerta buscarPorId(Integer id);

    List<Alerta> listarPorFuncionario(Integer funcionarioId);

    List<AlertaResumenDTO> listarResumen();

    Alerta guardar(Alerta alerta);

    Alerta actualizar(Integer id, Alerta alerta);

    void eliminar(Integer id);
}