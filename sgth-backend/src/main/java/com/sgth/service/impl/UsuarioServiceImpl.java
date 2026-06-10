package com.sgth.service.impl;

import com.sgth.entity.Usuario;
import com.sgth.repository.UsuarioRepository;
import com.sgth.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setCreadoEn(LocalDateTime.now());

        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }

        return repository.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existente = buscarPorId(id);

        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());
        existente.setEmail(usuario.getEmail());
        existente.setActivo(usuario.getActivo());
        existente.setFuncionario(usuario.getFuncionario());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuario.setActivo(false);
        repository.save(usuario);
    }
}