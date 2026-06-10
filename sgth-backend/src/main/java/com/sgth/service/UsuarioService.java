package com.sgth.service;

import com.sgth.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listarTodos();

    Usuario buscarPorId(Integer id);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Integer id, Usuario usuario);

    void eliminar(Integer id);
}