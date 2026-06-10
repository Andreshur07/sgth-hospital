package com.sgth.repository;

import com.sgth.entity.ExperienciaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienciaLaboralRepository
        extends JpaRepository<ExperienciaLaboral, Integer> {

    List<ExperienciaLaboral> findByFuncionarioId(Integer funcionarioId);
}