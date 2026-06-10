package com.sgth.repository;

import com.sgth.entity.Capacitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapacitacionRepository extends JpaRepository<Capacitacion, Integer> {

    List<Capacitacion> findByFuncionarioId(Integer funcionarioId);
}