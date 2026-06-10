package com.sgth.repository;

import com.sgth.entity.RequisitoLegal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoLegalRepository extends JpaRepository<RequisitoLegal, Integer> {

    List<RequisitoLegal> findByFuncionarioId(Integer funcionarioId);

}