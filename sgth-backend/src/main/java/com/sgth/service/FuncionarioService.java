package com.sgth.service;

import com.sgth.dto.FuncionarioResumenDTO;
import com.sgth.entity.Funcionario;

import java.util.List;

public interface FuncionarioService {

    List<Funcionario> listarTodos();

    List<FuncionarioResumenDTO> listarResumen();

    Funcionario buscarPorId(Integer id);

    Funcionario guardar(Funcionario funcionario);

    Funcionario actualizar(Integer id, Funcionario funcionario);

    void eliminar(Integer id);
}