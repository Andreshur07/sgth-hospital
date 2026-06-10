package com.sgth.service;

import com.sgth.entity.TipoContrato;

import java.util.List;

public interface TipoContratoService {

    List<TipoContrato> listarTodos();

    TipoContrato buscarPorId(Integer id);
}