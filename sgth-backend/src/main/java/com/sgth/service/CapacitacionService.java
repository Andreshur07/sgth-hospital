package com.sgth.service;

import com.sgth.entity.Capacitacion;

import java.util.List;

public interface CapacitacionService {

    List<Capacitacion> listarTodos();

    Capacitacion buscarPorId(Integer id);

    List<Capacitacion> listarPorFuncionario(Integer funcionarioId);

    Capacitacion guardar(Capacitacion capacitacion);

    Capacitacion actualizar(Integer id, Capacitacion capacitacion);

    void eliminar(Integer id);
}