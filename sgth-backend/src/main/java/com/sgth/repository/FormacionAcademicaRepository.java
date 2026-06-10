package com.sgth.repository;

import com.sgth.entity.FormacionAcademica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormacionAcademicaRepository
        extends JpaRepository<FormacionAcademica, Integer> {

    List<FormacionAcademica> findByFuncionarioId(Integer funcionarioId);
}