package com.sgth.service.impl;

import com.sgth.entity.TipoContrato;
import com.sgth.repository.TipoContratoRepository;
import com.sgth.service.TipoContratoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratoServiceImpl implements TipoContratoService {

    private final TipoContratoRepository tipoContratoRepository;

    public TipoContratoServiceImpl(TipoContratoRepository tipoContratoRepository) {
        this.tipoContratoRepository = tipoContratoRepository;
    }

    @Override
    public List<TipoContrato> listarTodos() {
        return tipoContratoRepository.findAll();
    }

    @Override
    public TipoContrato buscarPorId(Integer id) {
        return tipoContratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de contrato no encontrado"));
    }
}