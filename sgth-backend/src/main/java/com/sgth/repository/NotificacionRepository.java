package com.sgth.repository;

import com.sgth.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByFuncionarioId(Integer funcionarioId);

    List<Notificacion> findByDocumentoId(Integer documentoId);
}