package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "formacion_academica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormacionAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private String nivel;

    private String titulo;

    private String institucion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "documento_soporte")
    private String documentoSoporte;
}
