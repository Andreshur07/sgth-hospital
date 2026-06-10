package com.sgth.service;

import com.sgth.entity.Notificacion;

import java.util.List;

public interface NotificacionService {

    List<Notificacion> listarTodos();

    Notificacion buscarPorId(Integer id);

    List<Notificacion> listarPorFuncionario(Integer funcionarioId);

    Notificacion guardar(Notificacion notificacion);

    Notificacion actualizar(Integer id, Notificacion notificacion);

    void eliminar(Integer id);
}