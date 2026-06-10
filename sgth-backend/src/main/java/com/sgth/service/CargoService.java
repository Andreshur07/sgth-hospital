package com.sgth.service;

import com.sgth.entity.Cargo;

import java.util.List;

public interface CargoService {

    List<Cargo> listarTodos();

    Cargo buscarPorId(Integer id);
}
