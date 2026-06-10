package com.sgth.service;

import com.sgth.entity.TipoDocumento;

import java.util.List;

public interface TipoDocumentoService {

    List<TipoDocumento> listarTodos();

    TipoDocumento buscarPorId(Integer id);

    TipoDocumento guardar(TipoDocumento tipoDocumento);

    TipoDocumento actualizar(Integer id, TipoDocumento tipoDocumento);

    void eliminar(Integer id);
}