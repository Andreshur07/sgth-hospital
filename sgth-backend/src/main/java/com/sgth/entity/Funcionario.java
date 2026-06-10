package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_documento", nullable = false, length = 20)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 30)
    private String numeroDocumento;

    @Column(length = 150)
    private String correo;

    @Column(length = 30)
    private String telefono;

    @Column(columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(length = 20)
    private String estado;

    @Column(length = 10)
    private String sexo;

    @Column(length = 50)
    private String nacionalidad;

    @Column(length = 80)
    private String pais;

    @Column(name = "lugar_expedicion", length = 100)
    private String lugarExpedicion;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Column(name = "estado_civil", length = 50)
    private String estadoCivil;

    @Column(name = "numero_hijos")
    private Integer numeroHijos;

    @Column(name = "primer_nombre", length = 80)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 80)
    private String segundoNombre;

    @Column(name = "primer_apellido", length = 80)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 80)
    private String segundoApellido;

    @Column(name = "clase_libreta_militar", length = 30)
    private String claseLibretaMilitar;

    @Column(name = "numero_libreta_militar", length = 50)
    private String numeroLibretaMilitar;

    @Column(name = "distrito_militar", length = 50)
    private String distritoMilitar;

    @Column(name = "pais_nacimiento", length = 80)
    private String paisNacimiento;

    @Column(name = "departamento_nacimiento", length = 80)
    private String departamentoNacimiento;

    @Column(name = "ciudad_nacimiento", length = 80)
    private String ciudadNacimiento;

    @Column(name = "pais_direccion", length = 80)
    private String paisDireccion;

    @Column(name = "departamento_direccion", length = 80)
    private String departamentoDireccion;

    @Column(name = "ciudad_direccion", length = 80)
    private String ciudadDireccion;

    @Column(name = "grupo_sanguineo", length = 10)
    private String grupoSanguineo;

    @Column(name = "grupo_etnico", length = 50)
    private String grupoEtnico;

    @Column(length = 120)
    private String profesion;

    @Column(length = 120)
    private String eps;

    @Column(name = "fondo_pensiones", length = 120)
    private String fondoPensiones;

    @Column(length = 120)
    private String arl;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "tipo_contrato_id")
    private TipoContrato tipoContrato;

    @ManyToOne
    @JoinColumn(name = "dependencia_id")
    private Dependencia dependencia;

    @ManyToOne
    @JoinColumn(name = "estado_funcionario_id")
    private EstadoFuncionario estadoFuncionario;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;
}