package com.sgth.repository;

import com.sgth.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesoRepository extends JpaRepository<Proceso, Integer> {
}