package com.sgth.service;

import com.sgth.entity.Documento;

import java.util.List;

import com.sgth.dto.DocumentoResumenDTO;

import java.util.stream.Collectors;

public interface DocumentoService {

    List<Documento> listarTodos();

    Documento buscarPorId(Integer id);

    List<Documento> listarPorFuncionario(Integer funcionarioId);

    List<DocumentoResumenDTO> listarResumen();

    Documento guardar(Documento documento);

    Documento actualizar(Integer id, Documento documento);

    void eliminar(Integer id);
}