package com.sgth.repository;

import com.sgth.entity.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Integer> {

    List<HistorialEstado> findByFuncionarioId(Integer funcionarioId);
}