package com.sgth.service.impl;

import com.sgth.entity.Documento;
import com.sgth.repository.DocumentoRepository;
import com.sgth.service.DocumentoService;
import org.springframework.stereotype.Service;
import com.sgth.dto.DocumentoResumenDTO;
import java.util.stream.Collectors;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository repository;

    public DocumentoServiceImpl(DocumentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Documento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Documento buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }

    @Override
    public List<Documento> listarPorFuncionario(Integer funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    @Override
    public Documento guardar(Documento documento) {
        documento.setCreadoEn(LocalDateTime.now());

        if (documento.getEstado() == null || documento.getEstado().isBlank()) {
            documento.setEstado("VIGENTE");
        }

        return repository.save(documento);
    }

    @Override
    public Documento actualizar(Integer id, Documento documento) {
        Documento existente = buscarPorId(id);

        existente.setFuncionario(documento.getFuncionario());
        existente.setTipoDocumento(documento.getTipoDocumento());
        existente.setNombreArchivo(documento.getNombreArchivo());
        existente.setRutaArchivo(documento.getRutaArchivo());
        existente.setFechaExpedicion(documento.getFechaExpedicion());
        existente.setFechaVencimiento(documento.getFechaVencimiento());
        existente.setEstado(documento.getEstado());
        existente.setObservacion(documento.getObservacion());

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Documento documento = buscarPorId(id);
        documento.setEstado("INACTIVO");
        repository.save(documento);
    }

    @Override
    public List<DocumentoResumenDTO> listarResumen() {

        return repository.findAll()
                .stream()
                .map(documento -> {

                    String funcionario = "";

                    if (documento.getFuncionario() != null) {

                        funcionario =
                                (
                                        (documento.getFuncionario().getPrimerNombre() != null ? documento.getFuncionario().getPrimerNombre() : "") + " " +
                                                (documento.getFuncionario().getSegundoNombre() != null ? documento.getFuncionario().getSegundoNombre() : "") + " " +
                                                (documento.getFuncionario().getPrimerApellido() != null ? documento.getFuncionario().getPrimerApellido() : "") + " " +
                                                (documento.getFuncionario().getSegundoApellido() != null ? documento.getFuncionario().getSegundoApellido() : "")
                                ).trim();
                    }

                    return new DocumentoResumenDTO(
                            documento.getId(),
                            documento.getNombreArchivo(),
                            documento.getTipoDocumento() != null
                                    ? documento.getTipoDocumento().getNombre()
                                    : null,
                            documento.getFechaVencimiento() != null
                                    ? documento.getFechaVencimiento().toString()
                                    : null,
                            documento.getEstado(),
                            funcionario
                    );
                })
                .collect(Collectors.toList());
    }
}