package com.sgth.repository;

import com.sgth.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {

    List<Alerta> findByFuncionarioId(Integer funcionarioId);
}