package com.sgth.service;

import com.sgth.entity.Proceso;
import java.util.List;

public interface ProcesoService {

    List<Proceso> listarTodos();

    Proceso buscarPorId(Integer id);

    Proceso guardar(Proceso proceso);

    Proceso actualizar(Integer id, Proceso proceso);

    void eliminar(Integer id);
}