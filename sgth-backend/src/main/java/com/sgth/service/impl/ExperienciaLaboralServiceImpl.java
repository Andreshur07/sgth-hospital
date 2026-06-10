package com.sgth.service.impl;

import com.sgth.entity.ExperienciaLaboral;
import com.sgth.repository.ExperienciaLaboralRepository;
import com.sgth.service.ExperienciaLaboralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienciaLaboralServiceImpl
        implements ExperienciaLaboralService {

    private final ExperienciaLaboralRepository repository;

    public ExperienciaLaboralServiceImpl(
            ExperienciaLaboralRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExperienciaLaboral> listarTodos() {
        return repository.findAll();
    }

    @Override
    public ExperienciaLaboral buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Experiencia laboral no encontrada"));
    }

    @Override
    public List<ExperienciaLaboral> listarPorFuncionario(
            Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public ExperienciaLaboral guardar(
            ExperienciaLaboral experiencia) {

        return repository.save(experiencia);
    }

    @Override
    public ExperienciaLaboral actualizar(
            Integer id,
            ExperienciaLaboral experiencia) {

        ExperienciaLaboral existente = buscarPorId(id);

        existente.setFuncionario(experiencia.getFuncionario());
        existente.setEmpresa(experiencia.getEmpresa());
        existente.setCargo(experiencia.getCargo());
        existente.setFechaInicio(experiencia.getFechaInicio());
        existente.setFechaFin(experiencia.getFechaFin());
        existente.setFunciones(experiencia.getFunciones());
        existente.setDocumentoSoporte(
                experiencia.getDocumentoSoporte());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}