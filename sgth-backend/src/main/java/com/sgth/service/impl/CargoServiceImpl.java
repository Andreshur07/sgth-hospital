package com.sgth.service.impl;

import com.sgth.entity.Cargo;
import com.sgth.repository.CargoRepository;
import com.sgth.service.CargoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo buscarPorId(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cargo no encontrado"));
    }
}
