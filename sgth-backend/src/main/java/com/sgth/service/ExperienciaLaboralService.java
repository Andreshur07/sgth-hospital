package com.sgth.service;

import com.sgth.entity.ExperienciaLaboral;

import java.util.List;

public interface ExperienciaLaboralService {

    List<ExperienciaLaboral> listarTodos();

    ExperienciaLaboral buscarPorId(Integer id);

    List<ExperienciaLaboral> listarPorFuncionario(Integer funcionarioId);

    ExperienciaLaboral guardar(ExperienciaLaboral experiencia);

    ExperienciaLaboral actualizar(Integer id, ExperienciaLaboral experiencia);

    void eliminar(Integer id);
}