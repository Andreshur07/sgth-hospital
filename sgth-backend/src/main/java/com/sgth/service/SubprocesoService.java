package com.sgth.service;

import com.sgth.entity.Subproceso;

import java.util.List;

public interface SubprocesoService {

    List<Subproceso> listarTodos();

    Subproceso buscarPorId(Integer id);

    List<Subproceso> listarPorProceso(Integer procesoId);

    Subproceso guardar(Subproceso subproceso);

    Subproceso actualizar(Integer id, Subproceso subproceso);

    void eliminar(Integer id);
}