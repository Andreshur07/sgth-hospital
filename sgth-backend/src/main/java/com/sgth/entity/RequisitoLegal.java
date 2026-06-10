package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "requisitos_legales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequisitoLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private String nombre;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    private Boolean obligatorio;

    private Boolean cumplido;

    @Column(name = "documento_soporte")
    private String documentoSoporte;
}