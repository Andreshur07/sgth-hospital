package com.sgth.repository;

import com.sgth.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    List<Documento> findByFuncionarioId(Integer funcionarioId);

    List<Documento> findByTipoDocumentoId(Integer tipoDocumentoId);
}