package com.sgth.service;

import com.sgth.entity.FormacionAcademica;

import java.util.List;

public interface FormacionAcademicaService {

    List<FormacionAcademica> listarTodos();

    FormacionAcademica buscarPorId(Integer id);

    FormacionAcademica guardar(FormacionAcademica formacionAcademica);

    FormacionAcademica actualizar(Integer id,
                                  FormacionAcademica formacionAcademica);

    void eliminar(Integer id);

    List<FormacionAcademica> buscarPorFuncionario(Integer funcionarioId);
}
