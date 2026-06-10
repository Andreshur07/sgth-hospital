package com.sgth.service.impl;

import com.sgth.entity.TipoDocumento;
import com.sgth.repository.TipoDocumentoRepository;
import com.sgth.service.TipoDocumentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository repository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoDocumento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public TipoDocumento buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tipo de documento no encontrado"));
    }

    @Override
    public TipoDocumento guardar(TipoDocumento tipoDocumento) {
        return repository.save(tipoDocumento);
    }

    @Override
    public TipoDocumento actualizar(
            Integer id,
            TipoDocumento tipoDocumento) {

        TipoDocumento existente = buscarPorId(id);

        existente.setNombre(tipoDocumento.getNombre());
        existente.setCategoria(tipoDocumento.getCategoria());
        existente.setObligatorio(tipoDocumento.getObligatorio());
        existente.setRequiereVencimiento(
                tipoDocumento.getRequiereVencimiento());
        existente.setActivo(tipoDocumento.getActivo());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}