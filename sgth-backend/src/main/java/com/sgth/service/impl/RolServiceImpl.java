package com.sgth.service.impl;

import com.sgth.entity.Rol;
import com.sgth.repository.RolRepository;
import com.sgth.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repository;

    public RolServiceImpl(RolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rol> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rol no encontrado"));
    }

    @Override
    public Rol guardar(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public Rol actualizar(Integer id, Rol rol) {

        Rol existente = buscarPorId(id);

        existente.setNombre(rol.getNombre());
        existente.setDescripcion(rol.getDescripcion());
        existente.setActivo(rol.getActivo());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
