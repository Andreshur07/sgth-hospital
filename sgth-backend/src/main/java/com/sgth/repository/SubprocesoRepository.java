package com.sgth.repository;

import com.sgth.entity.Subproceso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubprocesoRepository extends JpaRepository<Subproceso, Integer> {

    List<Subproceso> findByProcesoId(Integer procesoId);
}