package com.sgth.service.impl;

import com.sgth.entity.Notificacion;
import com.sgth.repository.NotificacionRepository;
import com.sgth.service.NotificacionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionServiceImpl(NotificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Notificacion> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Notificacion buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    @Override
    public List<Notificacion> listarPorFuncionario(Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public Notificacion guardar(Notificacion notificacion) {
        if (notificacion.getCreadoEn() == null) {
            notificacion.setCreadoEn(LocalDateTime.now());
        }

        if (notificacion.getEstado() == null || notificacion.getEstado().isBlank()) {
            notificacion.setEstado("PENDIENTE");
        }

        return repository.save(notificacion);
    }

    @Override
    public Notificacion actualizar(Integer id, Notificacion notificacion) {
        Notificacion existente = buscarPorId(id);

        existente.setFuncionario(notificacion.getFuncionario());
        existente.setDocumento(notificacion.getDocumento());
        existente.setAsunto(notificacion.getAsunto());
        existente.setMensaje(notificacion.getMensaje());
        existente.setCorreoDestino(notificacion.getCorreoDestino());
        existente.setFechaEnvio(notificacion.getFechaEnvio());
        existente.setEstado(notificacion.getEstado());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}