package com.sgth.repository;

import com.sgth.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository
        extends JpaRepository<TipoDocumento, Integer> {
}