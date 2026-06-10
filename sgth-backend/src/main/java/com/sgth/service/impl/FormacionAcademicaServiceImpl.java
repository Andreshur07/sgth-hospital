package com.sgth.service.impl;

import com.sgth.entity.FormacionAcademica;
import com.sgth.repository.FormacionAcademicaRepository;
import com.sgth.service.FormacionAcademicaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormacionAcademicaServiceImpl
        implements FormacionAcademicaService {

    private final FormacionAcademicaRepository repository;

    public FormacionAcademicaServiceImpl(
            FormacionAcademicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FormacionAcademica> listarTodos() {
        return repository.findAll();
    }

    @Override
    public FormacionAcademica buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Formación académica no encontrada"));
    }

    @Override
    public FormacionAcademica guardar(
            FormacionAcademica formacionAcademica) {
        return repository.save(formacionAcademica);
    }

    @Override
    public FormacionAcademica actualizar(
            Integer id,
            FormacionAcademica formacionAcademica) {

        FormacionAcademica existente = buscarPorId(id);

        existente.setFuncionario(
                formacionAcademica.getFuncionario());
        existente.setNivel(
                formacionAcademica.getNivel());
        existente.setTitulo(
                formacionAcademica.getTitulo());
        existente.setInstitucion(
                formacionAcademica.getInstitucion());
        existente.setFechaInicio(
                formacionAcademica.getFechaInicio());
        existente.setFechaFin(
                formacionAcademica.getFechaFin());
        existente.setDocumentoSoporte(
                formacionAcademica.getDocumentoSoporte());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<FormacionAcademica> buscarPorFuncionario(
            Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }
}
