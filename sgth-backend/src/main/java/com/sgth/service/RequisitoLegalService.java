package com.sgth.service;

import com.sgth.entity.RequisitoLegal;

import java.util.List;

public interface RequisitoLegalService {

    List<RequisitoLegal> listarTodos();

    RequisitoLegal buscarPorId(Integer id);

    List<RequisitoLegal> listarPorFuncionario(Integer funcionarioId);

    RequisitoLegal guardar(RequisitoLegal requisito);

    RequisitoLegal actualizar(Integer id, RequisitoLegal requisito);

    void eliminar(Integer id);
}