package com.sgth.service.impl;

import com.sgth.entity.RequisitoLegal;
import com.sgth.repository.RequisitoLegalRepository;
import com.sgth.service.RequisitoLegalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitoLegalServiceImpl implements RequisitoLegalService {

    private final RequisitoLegalRepository repository;

    public RequisitoLegalServiceImpl(RequisitoLegalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequisitoLegal> listarTodos() {
        return repository.findAll();
    }

    @Override
    public RequisitoLegal buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisito legal no encontrado"));
    }

    @Override
    public List<RequisitoLegal> listarPorFuncionario(Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public RequisitoLegal guardar(RequisitoLegal requisito) {
        return repository.save(requisito);
    }

    @Override
    public RequisitoLegal actualizar(Integer id, RequisitoLegal requisito) {

        RequisitoLegal existente = buscarPorId(id);

        existente.setFuncionario(requisito.getFuncionario());
        existente.setNombre(requisito.getNombre());
        existente.setFechaExpedicion(requisito.getFechaExpedicion());
        existente.setFechaVencimiento(requisito.getFechaVencimiento());
        existente.setObligatorio(requisito.getObligatorio());
        existente.setCumplido(requisito.getCumplido());
        existente.setDocumentoSoporte(requisito.getDocumentoSoporte());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}