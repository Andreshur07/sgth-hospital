package com.sgth.service;

import com.sgth.entity.Rol;

import java.util.List;

public interface RolService {

    List<Rol> listarTodos();

    Rol buscarPorId(Integer id);

    Rol guardar(Rol rol);

    Rol actualizar(Integer id, Rol rol);

    void eliminar(Integer id);
}
