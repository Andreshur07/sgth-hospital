package com.sgth.service.impl;

import com.sgth.entity.Funcionario;
import com.sgth.repository.FuncionarioRepository;
import com.sgth.service.FuncionarioService;
import org.springframework.stereotype.Service;
import com.sgth.dto.FuncionarioResumenDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Funcionario no encontrado con id: " + id));
    }

    @Override
    public List<FuncionarioResumenDTO> listarResumen() {

        return funcionarioRepository.findAll()
                .stream()
                .map(funcionario -> new FuncionarioResumenDTO(
                        funcionario.getId(),
                        funcionario.getNumeroDocumento(),

                        (
                                (funcionario.getPrimerNombre() != null ? funcionario.getPrimerNombre() : "") + " " +
                                        (funcionario.getSegundoNombre() != null ? funcionario.getSegundoNombre() : "") + " " +
                                        (funcionario.getPrimerApellido() != null ? funcionario.getPrimerApellido() : "") + " " +
                                        (funcionario.getSegundoApellido() != null ? funcionario.getSegundoApellido() : "")
                        ).trim(),

                        funcionario.getCargo() != null ? funcionario.getCargo().getNombre() : null,
                        funcionario.getDependencia() != null ? funcionario.getDependencia().getNombre() : null,
                        funcionario.getEstado()
                ))
                .toList();
    }

    @Override
    public Funcionario guardar(Funcionario funcionario) {

        funcionario.setCreadoEn(LocalDateTime.now());
        funcionario.setActualizadoEn(LocalDateTime.now());

        if (funcionario.getEstado() == null || funcionario.getEstado().isBlank()) {
            funcionario.setEstado("ACTIVO");
        }

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario actualizar(Integer id, Funcionario funcionario) {

        Funcionario existente = buscarPorId(id);

        existente.setTipoDocumento(funcionario.getTipoDocumento());
        existente.setNumeroDocumento(funcionario.getNumeroDocumento());
        existente.setPrimerNombre(funcionario.getPrimerNombre());
        existente.setSegundoNombre(funcionario.getSegundoNombre());
        existente.setPrimerApellido(funcionario.getPrimerApellido());
        existente.setSegundoApellido(funcionario.getSegundoApellido());
        existente.setCorreo(funcionario.getCorreo());
        existente.setTelefono(funcionario.getTelefono());
        existente.setDireccion(funcionario.getDireccion());
        existente.setFechaNacimiento(funcionario.getFechaNacimiento());
        existente.setFechaIngreso(funcionario.getFechaIngreso());
        existente.setSexo(funcionario.getSexo());
        existente.setNacionalidad(funcionario.getNacionalidad());
        existente.setPais(funcionario.getPais());
        existente.setLugarExpedicion(funcionario.getLugarExpedicion());
        existente.setFechaExpedicion(funcionario.getFechaExpedicion());
        existente.setEstadoCivil(funcionario.getEstadoCivil());
        existente.setNumeroHijos(funcionario.getNumeroHijos());
        existente.setClaseLibretaMilitar(funcionario.getClaseLibretaMilitar());
        existente.setNumeroLibretaMilitar(funcionario.getNumeroLibretaMilitar());
        existente.setDistritoMilitar(funcionario.getDistritoMilitar());
        existente.setPaisNacimiento(funcionario.getPaisNacimiento());
        existente.setDepartamentoNacimiento(funcionario.getDepartamentoNacimiento());
        existente.setCiudadNacimiento(funcionario.getCiudadNacimiento());

        existente.setPaisDireccion(funcionario.getPaisDireccion());
        existente.setDepartamentoDireccion(funcionario.getDepartamentoDireccion());
        existente.setCiudadDireccion(funcionario.getCiudadDireccion());

        existente.setGrupoSanguineo(funcionario.getGrupoSanguineo());
        existente.setGrupoEtnico(funcionario.getGrupoEtnico());

        existente.setProfesion(funcionario.getProfesion());
        existente.setEps(funcionario.getEps());
        existente.setFondoPensiones(funcionario.getFondoPensiones());
        existente.setArl(funcionario.getArl());

        existente.setCargo(funcionario.getCargo());
        existente.setTipoContrato(funcionario.getTipoContrato());
        existente.setDependencia(funcionario.getDependencia());
        existente.setEstadoFuncionario(funcionario.getEstadoFuncionario());

        if (funcionario.getEstadoFuncionario() != null &&
                funcionario.getEstadoFuncionario().getNombre() != null) {

            existente.setEstado(funcionario.getEstadoFuncionario().getNombre());

        } else {
            existente.setEstado(funcionario.getEstado());
        }
        existente.setActualizadoEn(LocalDateTime.now());

        return funcionarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {

        Funcionario funcionario = buscarPorId(id);

        funcionario.setEstado("INACTIVO");
        funcionario.setActualizadoEn(LocalDateTime.now());

        funcionarioRepository.save(funcionario);
    }
}